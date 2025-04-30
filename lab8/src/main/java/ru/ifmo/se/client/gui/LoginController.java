package ru.ifmo.se.client.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ru.ifmo.se.client.App;
import ru.ifmo.se.client.Client;

public class LoginController {
    private Client client;
    private App app;

    @FXML
    private Label warning;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    @FXML
    protected void onLoginButtonClick() throws Exception {
        client.setUsername(username.getText());
        client.setPassword(password.getText());

        client.start();

        if (client.isCorrectLogin()) {
            warning.setText("");
            app.setMainScene();
        }
        else {
            warning.setText("Wrong username or password");
        }
    }

    @FXML
    protected void onRegisterButtonClick() throws Exception {
        if (client.register(username.getText(), password.getText())) {
            warning.setText("");
            client.setUsername(username.getText());
            client.setPassword(password.getText());
            app.setMainScene();
        }
        else {
            warning.setText("This username is already used");
        }
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setApp(App app) {
        this.app = app;
    }
}