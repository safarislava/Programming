package ru.ifmo.se.client.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ru.ifmo.se.client.App;
import ru.ifmo.se.client.Client;

import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController {
    private Client client;
    private App app;

    private String wrongUsername;
    private String usernameUsed;

    @FXML
    private Label loginLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label warningLabel;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button enterButton;
    @FXML
    private Button registerButton;

    public void setLocalizeLabels(Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ru.ifmo.se.client.gui.localization.Labels", locale);
        loginLabel.setText(resourceBundle.getString("username"));
        passwordLabel.setText(resourceBundle.getString("password"));
        enterButton.setText(resourceBundle.getString("enter"));
        registerButton.setText(resourceBundle.getString("register"));
        wrongUsername = resourceBundle.getString("wrong username");
        usernameUsed = resourceBundle.getString("username used");
    }

    @FXML
    protected void onLoginButtonClick() throws Exception {
        client.setUsername(username.getText());
        client.setPassword(password.getText());

        client.start();

        if (client.isCorrectLogin()) {
            client.startSync();
            warningLabel.setText("");
            app.setMainScene();
        }
        else {
            warningLabel.setText(wrongUsername);
        }
    }

    @FXML
    protected void onRegisterButtonClick() throws Exception {
        if (client.register(username.getText(), password.getText())) {
            warningLabel.setText("");
            client.setUsername(username.getText());
            client.setPassword(password.getText());
            app.setMainScene();
        }
        else {
            warningLabel.setText(usernameUsed);
        }
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setApp(App app) {
        this.app = app;
    }
}