package ru.ifmo.se.client.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ru.ifmo.se.client.Client;
import ru.ifmo.se.general.entity.Coordinates;
import ru.ifmo.se.general.entity.OrganizationDto;

import java.util.HashMap;
import java.util.List;

public class VisualizationController {
    private final Canvas canvas;

    private double offsetX;
    private double offsetY;
    private static final double MAX_ORGANIZATION_SIZE = 100;
    private static final double MIN_ORGANIZATION_SIZE = 50;
    private static final int ORGANIZATION_BRIGHT = 100;

    private OrganizationDto infoOrganization;

    private final HashMap<String, Color> creatorColors = new HashMap<>();

    public VisualizationController(Canvas canvas, ScrollPane visualizationLayout, Client client) {
        this.canvas = canvas;

        visualizationLayout.fitToWidthProperty().set(true);
        visualizationLayout.fitToHeightProperty().set(true);

        canvas.setWidth(100);
        canvas.setHeight(100);

        offsetX = 0;
        offsetY = 0;

        canvas.setOnMouseClicked(event -> {
            double mouseX = event.getX() - offsetX;
            double mouseY = event.getY() - offsetY;
            boolean isPoint = false;

            List<OrganizationDto> organizations = client.getOrganizations();
            for (OrganizationDto o : organizations) {
                Coordinates coordinates = o.organization.getCoordinates();
                if (isPointInOrganization(coordinates.getX(), coordinates.getY(), getSize(o), mouseX, mouseY)) {
                    infoOrganization = o;
                    isPoint = true;
                    break;
                }
            }
            if (!isPoint) infoOrganization = null;
            updateVisualisation(organizations);
        });
    }

    private Color randomColor() {
        int r = Math.min((int) (Math.random() * 255) + ORGANIZATION_BRIGHT, 255);
        int g = Math.min((int) (Math.random() * 255) + ORGANIZATION_BRIGHT, 255);
        int b = Math.min((int) (Math.random() * 255) + ORGANIZATION_BRIGHT, 255);
        return Color.rgb(r, g, b);
    }

    private Color getCreatorColor(OrganizationDto organization) {
        if (creatorColors.get(organization.creator) != null) return creatorColors.get(organization.creator);

        creatorColors.put(organization.creator, randomColor());
        return creatorColors.get(organization.creator);
    }

    private synchronized void drawOrganization(GraphicsContext context, double x, double y, double size, Color color) {
        context.setFill(color);
        context.setStroke(Color.BLACK);
        context.setLineWidth(2);

        context.fillRect(x - 0.5 * size, y - 0.5 * size, size, size);
        context.strokeRect(x - 0.5 * size, y - 0.5 * size, size, size);
        context.fillPolygon(new double[]{x - 0.7 * size, x + 0.7 * size, x},
                new double[]{y - 0.5 * size, y - 0.5 * size, y - 0.9 * size}, 3);
        context.strokePolygon(new double[]{x - 0.7 * size, x + 0.7 * size, x},
                new double[]{y - 0.5 * size, y - 0.5 * size, y - 0.9 * size}, 3);
        context.fillRect(x - 0.6 * size, y + 0.5 * size, 1.2 * size, 0.05 * size);
        context.strokeRect(x - 0.6 * size, y + 0.5 * size, 1.2 * size, 0.05 * size);

        context.setFill(color);
        context.strokeRect(x - 0.4 * size, y - 0.4 * size, 0.3 * size, 0.3 * size);
        context.strokeRect(x + 0.1 * size, y - 0.4 * size, 0.3 * size, 0.3 * size);
        context.strokeRect(x - 0.4 * size, y + 0.1 * size, 0.3 * size, 0.3 * size);
        context.strokeRect(x + 0.1 * size, y + 0.1 * size, 0.3 * size, 0.3 * size);
    }

    private void resizeCanvas(List<OrganizationDto> organizations) {
        double leftBorder = organizations.stream()
                .map(o -> o.organization.getCoordinates().getX())
                .min(Double::compare).orElse(0d) - MAX_ORGANIZATION_SIZE + offsetX;
        double rightBorder = organizations.stream()
                .map(o -> o.organization.getCoordinates().getX())
                .max(Double::compare).orElse(0d) + MAX_ORGANIZATION_SIZE + offsetX;
        double topBorder = organizations.stream()
                .map(o -> o.organization.getCoordinates().getY())
                .min(Float::compare).orElse(0f) - MAX_ORGANIZATION_SIZE + offsetY;
        double bottomBorder = organizations.stream()
                .map(o -> o.organization.getCoordinates().getY())
                .max(Float::compare).orElse(0f) + MAX_ORGANIZATION_SIZE + offsetY;

        if (leftBorder < 0){
            canvas.setWidth(canvas.getWidth() - leftBorder);
            offsetX -= leftBorder;
        }

        if (rightBorder > canvas.getWidth()) {
            canvas.setWidth(rightBorder);
        }
        if (topBorder < 0){
            canvas.setHeight(canvas.getWidth() - topBorder);
            offsetY -= topBorder;
        }
        if (bottomBorder > canvas.getHeight()) {
            canvas.setHeight(bottomBorder);
        }
    }

    private void drawInfo(GraphicsContext context, OrganizationDto dto) {
        context.setFill(Color.WHITE);
        context.setStroke(Color.BLACK);
        context.setLineWidth(2);
        context.setFont(Font.font("Arial", 20));
        Coordinates coordinates = dto.organization.getCoordinates();
        context.fillRect(coordinates.getX() + offsetX - 100, coordinates.getY() + offsetY, 200, 100);
        context.strokeRect(coordinates.getX() + offsetX - 100, coordinates.getY() + offsetY, 200, 100);

        context.setFill(Color.BLACK);
        String text = String.format("@%d, %s%n%s%n#%d, $%d%n%s",
                dto.organization.getId(), dto.organization.getName(), dto.organization.getFullName(),
                dto.organization.getEmployeesCount(), dto.organization.getAnnualTurnover(), dto.creator);
        context.fillText(text, coordinates.getX() + offsetX - 95, coordinates.getY() + offsetY + 20);
    }

    public void updateVisualisation(List<OrganizationDto> organizations) {
        GraphicsContext context = canvas.getGraphicsContext2D();

        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        resizeCanvas(organizations);

        for (OrganizationDto organization : organizations) {
            double x = organization.organization.getCoordinates().getX(),
                    y = organization.organization.getCoordinates().getY();

            drawOrganization(context, x + offsetX, y + offsetY, getSize(organization), getCreatorColor(organization));
        }

        if (infoOrganization == null) return;
        drawInfo(context, infoOrganization);
    }

    private boolean isInsideTriangle(double px, double py, double ax, double ay, double bx, double by, double cx, double cy) {
        return sameSide(ax, ay, bx, by, cx, cy, px, py) &&
                sameSide(bx, by, cx, cy, ax, ay, px, py) &&
                sameSide(cx, cy, ax, ay, bx, by, px, py);
    }

    private boolean sameSide(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double cross1 = dx * (y3 - y1) - dy * (x3 - x1);
        double cross2 = dx * (y4 - y1) - dy * (x4 - x1);
        return cross1 * cross2 >= 0;
    }

    public boolean isPointInOrganization(double x, double y, double size, double mouseX, double mouseY) {
        double rectX = x - 0.5 * size;
        double rectY = y - 0.5 * size;
        if (mouseX >= rectX && mouseX <= rectX + size && mouseY >= rectY && mouseY <= rectY + size) {
            return true;
        }

        rectX = x - 0.6 * size;
        rectY = y + 0.5 * size;
        double rectWidth = 1.2 * size;
        double rectHeight = 0.05 * size;
        if (mouseX >= rectX && mouseX <= rectX + rectWidth && mouseY >= rectY && mouseY <= rectY + rectHeight) {
            return true;
        }

        double ax = x - 0.7 * size;
        double ay = y - 0.5 * size;
        double bx = x + 0.7 * size;
        double by = y - 0.5 * size;
        double cx = x;
        double cy = y - 0.9 * size;

        return isInsideTriangle(mouseX, mouseY, ax, ay, bx, by, cx, cy);
    }

    private int getSize(OrganizationDto organization) {
        return (int) Math.min(Math.max(MIN_ORGANIZATION_SIZE, organization.organization.getEmployeesCount()), MAX_ORGANIZATION_SIZE);
    }
}
