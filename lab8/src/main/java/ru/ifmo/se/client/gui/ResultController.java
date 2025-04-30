package ru.ifmo.se.client.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ResultController {
    private Stage stage;

    @FXML
    private Label textLabel;

    public void onOkButtonClick() {
        stage.close();
    }

    public void setText(String text) {
        textLabel.setText(text);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
