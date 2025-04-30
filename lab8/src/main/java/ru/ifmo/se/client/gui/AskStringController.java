package ru.ifmo.se.client.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AskStringController {
    private Stage stage;
    private String text;
    private boolean confirmed = false;

    @FXML
    private Label promptLabel;
    @FXML
    private TextField textField;

    public void onConfirmButtonClicked() {
        try {
            text = textField.getText();

            confirmed = true;
            stage.close();
        }
        catch (Exception e) {
            System.out.println(e.getCause().toString());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.show();
        }
    }

    public String getString() {
        resetConfirmation();
        return text;
    }

    public void setPrompt(String prompt) {
        promptLabel.setText(prompt);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public boolean isConfirmed(){
        return confirmed;
    }

    public void resetConfirmation(){
        confirmed = false;
    }
}
