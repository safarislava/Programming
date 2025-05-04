package ru.ifmo.se.client.gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Duration;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;
import ru.ifmo.se.client.App;
import ru.ifmo.se.client.Client;
import ru.ifmo.se.client.command.Action;
import ru.ifmo.se.general.contract.CodePhrase;
import ru.ifmo.se.general.contract.Response;
import ru.ifmo.se.general.entity.OrganizationDto;
import ru.ifmo.se.general.entity.OrganizationField;
import ru.ifmo.se.general.entity.OrganizationType;

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
    private boolean userEditing = false;

    private VisualizationController visualizationController;

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
    private TableColumn<OrganizationDto, OrganizationType> typeColumn;
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
    private ScrollPane visualizationLayout;
    @FXML
    private Canvas visualizationCanvas;

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

        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditStart(event -> userEditing = true);
        nameColumn.setOnEditCommit(event -> {
            int id = event.getRowValue().organization.getId();
            Response response = client.execute(Action.UPDATE_FIELD, new String[]{Integer.toString(id),
                    OrganizationField.NAME.toString(), event.getNewValue()});
            if (response.getContent().equals(CodePhrase.FAILED)) app.showError();
            userEditing = false;
        });
        nameColumn.setOnEditCancel(event -> userEditing = false);

        coordinateXColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        coordinateXColumn.setOnEditStart(event -> userEditing = true);
        coordinateXColumn.setOnEditCommit(event -> {
            int id = event.getRowValue().organization.getId();
            Response response = client.execute(Action.UPDATE_FIELD, new String[]{Integer.toString(id),
                    OrganizationField.COORDINATE_X.toString(), String.valueOf(event.getNewValue())});
            if (response.getContent().equals(CodePhrase.FAILED)) app.showError();
            userEditing = false;
        });
        coordinateXColumn.setOnEditCancel(event -> userEditing = false);

        coordinateYColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        coordinateYColumn.setOnEditStart(event -> userEditing = true);
        coordinateYColumn.setOnEditCommit(event -> {
            int id = event.getRowValue().organization.getId();
            Response response = client.execute(Action.UPDATE_FIELD, new String[]{Integer.toString(id),
                    OrganizationField.COORDINATE_Y.toString(), String.valueOf(event.getNewValue())});
            if (response.getContent().equals(CodePhrase.FAILED)) app.showError();
            userEditing = false;
        });
        coordinateYColumn.setOnEditCancel(event -> userEditing = false);

        annualTurnoverColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
        annualTurnoverColumn.setOnEditStart(event -> userEditing = true);
        annualTurnoverColumn.setOnEditCommit(event -> {
            int id = event.getRowValue().organization.getId();
            Response response = client.execute(Action.UPDATE_FIELD, new String[]{Long.toString(id),
                    OrganizationField.ANNUAL_TURNOVER.toString(), String.valueOf(event.getNewValue())});
            if (response.getContent().equals(CodePhrase.FAILED)) app.showError();
            userEditing = false;
        });
        annualTurnoverColumn.setOnEditCancel(event -> userEditing = false);

        fullNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fullNameColumn.setOnEditStart(event -> userEditing = true);
        fullNameColumn.setOnEditCommit(event -> {
            int id = event.getRowValue().organization.getId();
            Response response = client.execute(Action.UPDATE_FIELD, new String[]{Integer.toString(id),
                    OrganizationField.FULL_NAME.toString(), event.getNewValue()});
            if (response.getContent().equals(CodePhrase.FAILED)) app.showError();
            userEditing = false;
        });
        fullNameColumn.setOnEditCancel(event -> userEditing = false);

        typeColumn.setCellFactory(ChoiceBoxTableCell.forTableColumn(OrganizationType.values()));
        typeColumn.setOnEditStart(event -> userEditing = true);
        typeColumn.setOnEditCommit(event -> {
            int id = event.getRowValue().organization.getId();
            Response response = client.execute(Action.UPDATE_FIELD, new String[]{Integer.toString(id),
                    OrganizationField.TYPE.toString(), event.getNewValue().toString()});
            if (response.getContent().equals(CodePhrase.FAILED)) app.showError();
            userEditing = false;
        });
        typeColumn.setOnEditCancel(event -> userEditing = false);

        employeesCountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        employeesCountColumn.setOnEditStart(event -> userEditing = true);
        employeesCountColumn.setOnEditCommit(event -> {
            int id = event.getRowValue().organization.getId();
            Response response = client.execute(Action.UPDATE_FIELD, new String[]{Integer.toString(id),
                    OrganizationField.EMPLOYEES_COUNT.toString(), String.valueOf(event.getNewValue())});
            if (response.getContent().equals(CodePhrase.FAILED)) app.showError();
            userEditing = false;
        });
        employeesCountColumn.setOnEditCancel(event -> userEditing = false);

        zipcodeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        zipcodeColumn.setOnEditStart(event -> userEditing = true);
        zipcodeColumn.setOnEditCommit(event -> {
            int id = event.getRowValue().organization.getId();
            Response response = client.execute(Action.UPDATE_FIELD, new String[]{Integer.toString(id),
                    OrganizationField.ZIPCODE.toString(), event.getNewValue()});
            if (response.getContent().equals(CodePhrase.FAILED)) app.showError();
            userEditing = false;
        });
        zipcodeColumn.setOnEditCancel(event -> userEditing = false);

        townXColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        townXColumn.setOnEditStart(event -> userEditing = true);
        townXColumn.setOnEditCommit(event -> {
            int id = event.getRowValue().organization.getId();
            Response response = client.execute(Action.UPDATE_FIELD, new String[]{Integer.toString(id),
                    OrganizationField.TOWN_X.toString(), event.getNewValue().toString()});
            if (response.getContent().equals(CodePhrase.FAILED)) app.showError();
            userEditing = false;
        });
        townXColumn.setOnEditCancel(event -> userEditing = false);

        townYColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        townYColumn.setOnEditStart(event -> userEditing = true);
        townYColumn.setOnEditCommit(event -> {
            int id = event.getRowValue().organization.getId();
            Response response = client.execute(Action.UPDATE_FIELD, new String[]{Integer.toString(id),
                    OrganizationField.TOWN_Y.toString(), event.getNewValue().toString()});
            if (response.getContent().equals(CodePhrase.FAILED)) app.showError();
            userEditing = false;
        });
        townYColumn.setOnEditCancel(event -> userEditing = false);

        townZColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        townZColumn.setOnEditStart(event -> userEditing = true);
        townZColumn.setOnEditCommit(event -> {
            int id = event.getRowValue().organization.getId();
            Response response = client.execute(Action.UPDATE_FIELD, new String[]{Integer.toString(id),
                    OrganizationField.TOWN_Z.toString(), event.getNewValue().toString()});
            if (response.getContent().equals(CodePhrase.FAILED)) app.showError();
            userEditing = false;
        });
        townZColumn.setOnEditCancel(event -> userEditing = false);

        visualizationController = new VisualizationController(visualizationCanvas, visualizationLayout, client);
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
        visualizationController.updateVisualisation(organizations);
    }

    private synchronized void updateTable(List<OrganizationDto> organizations) {
        if (userEditing) return;

        ObservableList<OrganizationDto> items = FXCollections.observableArrayList(organizations);

        int id = mainTable.getSelectionModel().getSelectedIndex();
        mainTable.getColumns().clear();

        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().organization.getId()).asObject());
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().organization.getName()));
        coordinateXColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().organization.getCoordinates().getX()).asObject());
        coordinateYColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().organization.getCoordinates().getY()).asObject());
        creationTimeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().organization.getCreationDate().withZoneSameInstant(ZoneId.systemDefault()).toString()));
        annualTurnoverColumn.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().organization.getAnnualTurnover()).asObject());
        fullNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().organization.getFullName()));
        employeesCountColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().organization.getEmployeesCount()).asObject());
        typeColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().organization.getType()));
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
        mainTable.getSelectionModel().select(id);
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
}
