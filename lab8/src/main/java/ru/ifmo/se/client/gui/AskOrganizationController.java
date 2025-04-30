package ru.ifmo.se.client.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ru.ifmo.se.general.entity.*;

public class AskOrganizationController {
    private Stage stage;
    private Organization organization;
    private OrganizationType organizationType;
    private int organizationId;
    private boolean confirmed = false;

    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField coordinateX;
    @FXML
    private TextField coordinateY;
    @FXML
    private TextField annualTurnover;
    @FXML
    private TextField fullName;
    @FXML
    private TextField employeesCount;
    @FXML
    private ChoiceBox<OrganizationType> type;
    @FXML
    private TextField zipcode;
    @FXML
    private TextField townX;
    @FXML
    private TextField townY;
    @FXML
    private TextField townZ;
    @FXML
    private GridPane idGrid;
    @FXML
    private GridPane mainGrid;
    @FXML
    private GridPane typeGrid;
    @FXML
    private GridPane locationGrid;

    private Organization setOrganization() {
        Organization organization = new Organization();
        organization.setName(name.getText());
        organization.setCoordinates(new Coordinates(
                Double.parseDouble(coordinateX.getText()),
                Float.parseFloat(coordinateY.getText())));
        organization.setCreationDate();
        organization.setAnnualTurnover(Long.parseLong(annualTurnover.getText()));
        organization.setFullName(fullName.getText());
        organization.setEmployeesCount(Integer.parseInt(employeesCount.getText()));
        organization.setType(type.getValue());
        organization.setPostalAddress(new Address(zipcode.getText(), new Location(
                Double.parseDouble(townX.getText()),
                Long.parseLong(townY.getText()),
                Float.parseFloat(townZ.getText()))));

        return organization;
    }

    public void onConfirmButtonClicked() {
        try {
            if (!mainGrid.isDisabled() && !typeGrid.isDisabled() && !locationGrid.isDisabled()) {
                organization = setOrganization();
                if (!idGrid.isDisabled()) organization.setId(Integer.parseInt(id.getText()));
            }
            else if (!idGrid.isDisabled()) organizationId = Integer.parseInt(id.getText());
            else if (!typeGrid.isDisabled()) organizationType = type.getValue();

            confirmed = true;
            stage.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.show();
        }
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void resetConfirmation() {
        confirmed = false;
    }

    public Organization getOrganization() {
        return organization;
    }

    public Organization getOrganizationWithId() {
        resetConfirmation();
        return organization;
    }

    public int getOrganizationId() {
        resetConfirmation();
        return organizationId;
    }

    public OrganizationType getOrganizationType() {
        resetConfirmation();
        return organizationType;
    }

    public void defaultConfig() {
        ObservableList<OrganizationType> types = FXCollections.observableArrayList(OrganizationType.values());
        type.setItems(types);
    }

    public void setOrganizationConfig() {
        defaultConfig();
        idGrid.setDisable(true);
        mainGrid.setDisable(false);
        typeGrid.setDisable(false);
        locationGrid.setDisable(false);
    }

    public void setOrganizationIdConfig() {
        defaultConfig();
        idGrid.setDisable(false);
        mainGrid.setDisable(false);
        typeGrid.setDisable(false);
        locationGrid.setDisable(false);
    }

    public void setOrganizationTypeConfig() {
        defaultConfig();
        idGrid.setDisable(true);
        mainGrid.setDisable(true);
        typeGrid.setDisable(false);
        locationGrid.setDisable(true);
    }

    public void setIdConfig() {
        idGrid.setDisable(false);
        mainGrid.setDisable(true);
        typeGrid.setDisable(true);
        locationGrid.setDisable(true);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
