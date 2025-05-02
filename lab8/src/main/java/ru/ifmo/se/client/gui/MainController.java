package ru.ifmo.se.client.gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.util.Duration;
import ru.ifmo.se.client.App;
import ru.ifmo.se.client.Client;
import ru.ifmo.se.client.command.Action;
import ru.ifmo.se.general.contract.CodePhrase;
import ru.ifmo.se.general.entity.OrganizationDto;

import java.io.File;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainController {
    private Client client;
    private App app;

    private Timeline updateTimeline;
    private static final int UPDATE_THRESHOLD = 5;

    @FXML
    private MenuItem logOutButton;
    @FXML
    private Tab tableTab;
    @FXML
    private Menu editMenu;
    @FXML
    private MenuItem insertItem;
    @FXML
    private MenuItem updateItem;
    @FXML
    private MenuItem removeIdItem;
    @FXML
    private MenuItem removeGreaterIdItem;
    @FXML
    private MenuItem removeGreaterItem;
    @FXML
    private MenuItem removeLowerItem;
    @FXML
    private Menu filterMenu;
    @FXML
    private MenuItem defaultItem;
    @FXML
    private MenuItem containsNameItem;
    @FXML
    private MenuItem fullNameItem;
    @FXML
    private Menu executeMenu;
    @FXML
    private MenuItem countLessTypeItem;
    @FXML
    private MenuItem executeScriptItem;
    @FXML
    private MenuItem infoItem;
    @FXML
    private Menu helpMenu;
    @FXML
    private TableColumn<OrganizationDto, Integer> idColumn;
    @FXML
    private TableColumn<OrganizationDto, String> nameColumn;
    @FXML
    private TableColumn<OrganizationDto, Double> coordinateXColumn;
    @FXML
    private TableColumn<OrganizationDto, Double> coordinateYColumn;
    @FXML
    private TableColumn<OrganizationDto, String> creationTimeColumn;
    @FXML
    private TableColumn<OrganizationDto, Long> annualTurnoverColumn;
    @FXML
    private TableColumn<OrganizationDto, String> fullNameColumn;
    @FXML
    private TableColumn<OrganizationDto, Integer> employeesCountColumn;
    @FXML
    private TableColumn<OrganizationDto, String> typeColumn;
    @FXML
    private TableColumn<OrganizationDto, String> zipcodeColumn;
    @FXML
    private TableColumn<OrganizationDto, Double> townXColumn;
    @FXML
    private TableColumn<OrganizationDto, Double> townYColumn;
    @FXML
    private TableColumn<OrganizationDto, Float> townZColumn;
    @FXML
    private TableColumn<OrganizationDto, String> creatorColumn;
    @FXML
    private Tab mapTab;
    @FXML
    private TableView<OrganizationDto> mainTable;
    @FXML
    private MenuButton usernameButton;
    @FXML
    private MenuItem ruItem;
    @FXML
    private MenuItem enItem;
    @FXML
    private MenuItem geItem;
    @FXML
    private MenuItem huItem;
    @FXML
    private Canvas visualisationCanvas;

    public void setLocalizeLabels(Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ru.ifmo.se.client.gui.localization.Labels", locale);
        logOutButton.setText(resourceBundle.getString("log out"));
        tableTab.setText(resourceBundle.getString("table"));
        editMenu.setText(resourceBundle.getString("edit"));
        insertItem.setText(resourceBundle.getString("insert"));
        updateItem.setText(resourceBundle.getString("update"));
        removeIdItem.setText(resourceBundle.getString("remove id"));
        removeGreaterIdItem.setText(resourceBundle.getString("remove greater id"));
        removeGreaterItem.setText(resourceBundle.getString("remove greater"));
        removeLowerItem.setText(resourceBundle.getString("remove lower"));
        filterMenu.setText(resourceBundle.getString("filter"));
        defaultItem.setText(resourceBundle.getString("default"));
        containsNameItem.setText(resourceBundle.getString("contains name"));
        fullNameItem.setText(resourceBundle.getString("full name"));
        executeMenu.setText(resourceBundle.getString("execute"));
        countLessTypeItem.setText(resourceBundle.getString("count less type"));
        executeScriptItem.setText(resourceBundle.getString("execute script"));
        infoItem.setText(resourceBundle.getString("info"));
        helpMenu.setText(resourceBundle.getString("help"));
        idColumn.setText(resourceBundle.getString("id"));
        nameColumn.setText(resourceBundle.getString("name"));
        coordinateXColumn.setText(resourceBundle.getString("coordinate x"));
        coordinateYColumn.setText(resourceBundle.getString("coordinate y"));
        creationTimeColumn.setText(resourceBundle.getString("creation time"));
        annualTurnoverColumn.setText(resourceBundle.getString("annual turnover"));
        fullNameColumn.setText(resourceBundle.getString("full name"));
        typeColumn.setText(resourceBundle.getString("type"));
        employeesCountColumn.setText(resourceBundle.getString("employees count"));
        zipcodeColumn.setText(resourceBundle.getString("zipcode"));
        townXColumn.setText(resourceBundle.getString("town x"));
        townYColumn.setText(resourceBundle.getString("town y"));
        townZColumn.setText(resourceBundle.getString("town z"));
        mapTab.setText(resourceBundle.getString("map"));
        ruItem.setText(resourceBundle.getString("set ru"));
        enItem.setText(resourceBundle.getString("set en"));
        geItem.setText(resourceBundle.getString("set ge"));
        huItem.setText(resourceBundle.getString("set hu"));
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public void prepare() {
        setUsernameButton();
        setupAutoUpdate();
    }

    private void setupAutoUpdate() {
        if (updateTimeline != null) {
            updateTimeline.stop();
        }

        updateTimeline = new Timeline(
                new KeyFrame(Duration.seconds(UPDATE_THRESHOLD), e -> updateOrganization())
        );

        updateTimeline.setCycleCount(Timeline.INDEFINITE);
        updateTimeline.play();
    }

    public void setUsernameButton() {
        usernameButton.setText(client.getUsername());
    }

    public void logOut() {
        client.stopSync();
        client.setUsername(null);
        client.setPassword(null);

        if (updateTimeline != null) {
            updateTimeline.stop();
        }

        try {
            app.setLoginScene();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateOrganization() {
        List<OrganizationDto> organizations = client.getOrganizations();
        updateTable(organizations);
        updateVisualisation(organizations);
    }

    public synchronized void updateTable(List<OrganizationDto> organizations) {
        ObservableList<OrganizationDto> items = FXCollections.observableArrayList(organizations);

        mainTable.getColumns().clear();

        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().organization.getId()).asObject());
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().organization.getName()));
        coordinateXColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().organization.getCoordinates().getX()).asObject());
        coordinateYColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().organization.getCoordinates().getY()).asObject());
        creationTimeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().organization.getCreationDate().withZoneSameInstant(ZoneId.systemDefault()).toString()));
        annualTurnoverColumn.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().organization.getAnnualTurnover()).asObject());
        fullNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().organization.getFullName()));
        employeesCountColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().organization.getEmployeesCount()).asObject());
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().organization.getType().toString()));
        zipcodeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().organization.getPostalAddress().getZipCode()));
        townXColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().organization.getPostalAddress().getTown().getX()).asObject());
        townYColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().organization.getPostalAddress().getTown().getY()).asObject());
        townZColumn.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().organization.getPostalAddress().getTown().getZ()).asObject());
        creatorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().creator));

        mainTable.getColumns().addAll(
                idColumn, nameColumn, coordinateXColumn, coordinateYColumn, creationTimeColumn,
                annualTurnoverColumn, fullNameColumn, employeesCountColumn, typeColumn,
                zipcodeColumn, townXColumn, townYColumn, townZColumn, creatorColumn);

        mainTable.setItems(items);
    }

    public void insert() {
        String result = client.execute(Action.INSERT, new String[0]).getContent();
        if (result.equals(CodePhrase.FAILED)) {app.showError();}
        updateOrganization();
    }

    public void update() {
        String result = client.execute(Action.UPDATE, new String[0]).getContent();
        if (result.equals(CodePhrase.FAILED)) {app.showError();}
        updateOrganization();
    }

    public void removeId() {
        String result = client.execute(Action.REMOVE_ID, new String[0]).getContent();
        if (result.equals(CodePhrase.FAILED)) {app.showError();}
        updateOrganization();
    }

    public void removeGreaterId() {
        String result = client.execute(Action.REMOVE_GREATER_ID, new String[0]).getContent();
        if (result.equals(CodePhrase.FAILED)) {app.showError();}
        updateOrganization();
    }

    public void removeGreater() {
        String result = client.execute(Action.REMOVE_GREATER, new String[0]).getContent();
        if (result.equals(CodePhrase.FAILED)) {app.showError();}
        updateOrganization();
    }

    public void removeLower() {
        String result = client.execute(Action.REMOVE_LOWER, new String[0]).getContent();
        if (result.equals(CodePhrase.FAILED)) {app.showError();}
        updateOrganization();
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

            updateOrganization();
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

    public void setRuLocale() {
        app.setLocale(new Locale("ru"));
        try {
            app.setMainScene();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setEnLocale() {
        app.setLocale(new Locale("en"));
        try {
            app.setMainScene();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setGeLocale() {
        app.setLocale(new Locale("ge"));
        try {
            app.setMainScene();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setHuLocale() {
        app.setLocale(new Locale("hu"));
        try {
            app.setMainScene();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setDefaultFilter() {
        client.setFilter(FilterType.getAction(FilterType.DEFAULT), new String[0]);
        updateOrganization();
    }

    public void setFilterContainsName() {
        try {
            AskStringController askStringController = app.openAskStringScene("Name");
            client.setFilter(FilterType.getAction(FilterType.FILTER_CONTAINS_NAME), new String[]{askStringController.getString()});
            updateOrganization();
        } catch (Exception e) {
            app.showError();
            throw new RuntimeException(e);
        }
    }

    public void setFilterFullName() {
        try {
            AskStringController askStringController = app.openAskStringScene("Full Name");
            client.setFilter(FilterType.getAction(FilterType.FILTER_FULL_NAME), new String[]{askStringController.getString()});
            updateOrganization();
        } catch (Exception e) {
            app.showError();
            throw new RuntimeException(e);
        }
    }

    private synchronized void updateVisualisation(List<OrganizationDto> organizations) {

    }
}
