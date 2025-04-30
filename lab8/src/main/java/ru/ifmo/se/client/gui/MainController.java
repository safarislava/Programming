package ru.ifmo.se.client.gui;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ru.ifmo.se.client.App;
import ru.ifmo.se.client.Client;
import ru.ifmo.se.client.command.Action;
import ru.ifmo.se.general.contract.CodePhrase;
import ru.ifmo.se.general.contract.OrganizationConverter;
import ru.ifmo.se.general.entity.OrganizationDto;

import java.io.File;
import java.util.ArrayList;

public class MainController {
    private Client client;
    private App app;

    private Action filterAction = FilterType.getAction(FilterType.DEFAULT);
    private final ArrayList<String> filterArgs = new ArrayList<>();

    @FXML
    private TableView<OrganizationDto> mainTable;
    @FXML
    private MenuButton usernameButton;

    public void setClient(Client client) {
        this.client = client;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public void prepare() {
        setUsernameButton();
        updateTable();
    }

    public void setUsernameButton() {
        usernameButton.setText(client.getUsername());
    }

    @FXML
    protected void logOut() throws Exception {
        client.setUsername(null);
        client.setPassword(null);
        app.setLoginScene();
    }

    public void updateTable() {
        String text = client.execute(filterAction, filterArgs.toArray(new String[0])).getContent();

        ObservableList<OrganizationDto> items = FXCollections.observableArrayList(OrganizationConverter.decode(text));

        mainTable.getColumns().clear();

        TableColumn<OrganizationDto, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().organization.getId()).asObject());

        TableColumn<OrganizationDto, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().organization.getName()));

        TableColumn<OrganizationDto, Double> coordinateXColumn = new TableColumn<>("Coordinate X");
        coordinateXColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().organization.getCoordinates().getX()).asObject());

        TableColumn<OrganizationDto, Double> coordinateYColumn = new TableColumn<>("Coordinate Y");
        coordinateYColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().organization.getCoordinates().getY()).asObject());

        TableColumn<OrganizationDto, String> creationDateColumn = new TableColumn<>("Creation Time");
        creationDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().organization.getCreationDate().toString()));

        TableColumn<OrganizationDto, Long> turnoverColumn = new TableColumn<>("Annual Turnover");
        turnoverColumn.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().organization.getAnnualTurnover()).asObject());

        TableColumn<OrganizationDto, String> fullNameColumn = new TableColumn<>("Full Name");
        fullNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().organization.getFullName()));

        TableColumn<OrganizationDto, Integer> employeesColumn = new TableColumn<>("Employees Count");
        employeesColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().organization.getEmployeesCount()).asObject());

        TableColumn<OrganizationDto, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().organization.getType().toString()));

        TableColumn<OrganizationDto, String> zipcodeColumn = new TableColumn<>("Postal Zipcode");
        zipcodeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().organization.getPostalAddress().getZipCode()));

        TableColumn<OrganizationDto, Double> townXColumn = new TableColumn<>("Postal Town X");
        townXColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().organization.getPostalAddress().getTown().getX()).asObject());

        TableColumn<OrganizationDto, Double> townYColumn = new TableColumn<>("Postal Town Y");
        townYColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().organization.getPostalAddress().getTown().getY()).asObject());

        TableColumn<OrganizationDto, Float> townZColumn = new TableColumn<>("Postal Town Z");
        townZColumn.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().organization.getPostalAddress().getTown().getZ()).asObject());

        TableColumn<OrganizationDto, String> creatorColumn = new TableColumn<>("Creator");
        creatorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().creator));

        mainTable.getColumns().addAll(
                idColumn, nameColumn, coordinateXColumn, coordinateYColumn, creationDateColumn,
                turnoverColumn, fullNameColumn, employeesColumn, typeColumn,
                zipcodeColumn, townXColumn, townYColumn, townZColumn, creatorColumn);

        mainTable.setItems(items);
    }

    public void insert() {
        String result = client.execute(Action.INSERT, new String[0]).getContent();
        if (result.equals(CodePhrase.FAILED)) {app.showError();}
        updateTable();
    }

    public void update() {
        String result = client.execute(Action.UPDATE, new String[0]).getContent();
        if (result.equals(CodePhrase.FAILED)) {app.showError();}
        updateTable();
    }

    public void removeId() {
        String result = client.execute(Action.REMOVE_ID, new String[0]).getContent();
        if (result.equals(CodePhrase.FAILED)) {app.showError();}
        updateTable();
    }

    public void removeGreaterId() {
        String result = client.execute(Action.REMOVE_GREATER_ID, new String[0]).getContent();
        if (result.equals(CodePhrase.FAILED)) {app.showError();}
        updateTable();
    }

    public void removeGreater() {
        String result = client.execute(Action.REMOVE_GREATER, new String[0]).getContent();
        if (result.equals(CodePhrase.FAILED)) {app.showError();}
        updateTable();
    }

    public void removeLower() {
        String result = client.execute(Action.REMOVE_LOWER, new String[0]).getContent();
        if (result.equals(CodePhrase.FAILED)) {app.showError();}
        updateTable();
    }

    public void countLessType() {
        String result = client.execute(Action.COUNT_LESS_THAN_TYPE, new String[0]).getContent();
        if (result.equals(CodePhrase.FAILED)) {app.showError();}

        try {
            app.openResultScene(String.format("Count of organizations with type less: %s", result));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void executeScript() {
        try {
            File script = app.openAskFilePathScene();

            String result = client.execute(Action.EXECUTE_SCRIPT,
                    new String[]{script.getAbsoluteFile().toString()}).getContent();
            if (result.equals(CodePhrase.FAILED)) {app.showError();}

            updateTable();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void info() {
        String result = client.execute(Action.INFO, new String[0]).getContent();
        if (result.equals(CodePhrase.FAILED)) {app.showError();}

        try {
            app.openResultScene(String.format("Info about collection: %s", result));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void help() {
        String result = client.execute(Action.HELP, new String[0]).getContent();
        if (result.equals(CodePhrase.FAILED)) {app.showError();}

        try {
            app.openResultScene(String.format(result));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setDefaultFilter() {
        filterAction = FilterType.getAction(FilterType.DEFAULT);
        filterArgs.clear();
        updateTable();
    }

    public void setFilterContainsName() {
        try {
            filterAction = FilterType.getAction(FilterType.FILTER_CONTAINS_NAME);
            AskStringController askStringController = app.openAskStringScene("Name");
            filterArgs.clear();
            filterArgs.add(askStringController.getString());
            updateTable();
        } catch (Exception e) {
            app.showError();
            throw new RuntimeException(e);
        }
    }

    public void setFilterFullName() {
        try {
            filterAction = FilterType.getAction(FilterType.FILTER_FULL_NAME);
            AskStringController askStringController = app.openAskStringScene("Full Name");
            filterArgs.clear();
            filterArgs.add(askStringController.getString());
            updateTable();
        } catch (Exception e) {
            app.showError();
            throw new RuntimeException(e);
        }
    }
}
