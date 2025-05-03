package ru.ifmo.se.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.ifmo.se.client.gui.*;
import ru.ifmo.se.client.parser.GuiParser;
import ru.ifmo.se.general.parser.Parser;


import java.io.File;
import java.util.Locale;

public class App extends Application {
    private Client client;

    private Stage primaryStage;
    private Stage askOrganizationStage;
    private Stage askStringStage;
    private Stage resultStage;

    private Locale locale;

    public static void main(String[] args) {
        launch(args);
    }

    private void initialize() {
        Parser parser = new GuiParser(this);
        client = new Client("127.0.0.1", 8012, parser); // "185.239.141.48"

        locale = Locale.getDefault();

        askOrganizationStage = new Stage();
        askOrganizationStage.initOwner(primaryStage);
        askOrganizationStage.initModality(Modality.APPLICATION_MODAL);
        askOrganizationStage.setResizable(false);

        askStringStage = new Stage();
        askStringStage.initOwner(primaryStage);
        askStringStage.initModality(Modality.APPLICATION_MODAL);
        askStringStage.setResizable(false);

        resultStage = new Stage();
        resultStage.initOwner(primaryStage);
        resultStage.initModality(Modality.APPLICATION_MODAL);
        resultStage.setResizable(false);

        primaryStage.setOnCloseRequest(event -> System.exit(0));
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        initialize();
        setLoginScene();
        stage.show();
    }

    public void setLoginScene() throws Exception {
        FXMLLoader loginWindowLoader = new FXMLLoader(getClass().getResource("/ru/ifmo/se/client/login-form.fxml"));
        Scene loginWindowScene = new Scene(loginWindowLoader.load(), 800, 600);
        LoginController loginController = loginWindowLoader.getController();

        loginController.setClient(client);
        loginController.setApp(this);
        loginController.setLocalizeLabels(locale);

        primaryStage.setScene(loginWindowScene);
    }

    public void setMainScene() throws Exception {
        FXMLLoader mainWindowLoader = new FXMLLoader(getClass().getResource("/ru/ifmo/se/client/main-form.fxml"));
        Scene mainWindowScene = new Scene(mainWindowLoader.load(), 800, 600);
        MainController mainController = mainWindowLoader.getController();

        mainController.setClient(client);
        mainController.setApp(this);
        mainController.prepare();
        mainController.setLocalizeLabels(locale);

        client.syncServer();
        mainController.updateOrganization();

        primaryStage.setScene(mainWindowScene);
    }

    public AskOrganizationController openAskOrganizationScene(AskWindowType type) throws Exception {
        FXMLLoader askOrganizationWindowLoader = new FXMLLoader(getClass().getResource("/ru/ifmo/se/client/ask-organization-form.fxml"));
        Scene askOrganizationWindowScene = new Scene(askOrganizationWindowLoader.load(), 225, 460);
        AskOrganizationController askOrganizationController = getAskOrganizationController(type, askOrganizationWindowLoader);

        askOrganizationStage.setScene(askOrganizationWindowScene);
        askOrganizationStage.showAndWait();

        return askOrganizationController;
    }

    private AskOrganizationController getAskOrganizationController(AskWindowType type, FXMLLoader askOrganizationWindowLoader) {
        AskOrganizationController askOrganizationController = askOrganizationWindowLoader.getController();
        askOrganizationController.setStage(askOrganizationStage);

        askOrganizationController.setLocalizeLabels(locale);
        switch (type) {
            case ID -> askOrganizationController.setIdConfig();
            case ORGANIZATION -> askOrganizationController.setOrganizationConfig();
            case ORGANIZATION_WITH_ID -> askOrganizationController.setOrganizationIdConfig();
            case ORGANIZATION_TYPE -> askOrganizationController.setOrganizationTypeConfig();
        }
        return askOrganizationController;
    }

    public AskStringController openAskStringScene(String prompt) throws Exception {
        FXMLLoader askStringWindowLoader = new FXMLLoader(getClass().getResource("/ru/ifmo/se/client/ask-string-form.fxml"));
        Scene askWindowScene = new Scene(askStringWindowLoader.load(), 300, 100);
        AskStringController askStringController = askStringWindowLoader.getController();

        askStringController.setStage(askStringStage);
        askStringController.setPrompt(prompt);

        askStringStage.setScene(askWindowScene);
        askStringStage.showAndWait();

        return askStringController;
    }

    public void openResultScene(String text) throws Exception {
        FXMLLoader resultWindowLoader = new FXMLLoader(getClass().getResource("/ru/ifmo/se/client/result-form.fxml"));
        Scene resultWindowScene = new Scene(resultWindowLoader.load());
        ResultController resultController = resultWindowLoader.getController();

        resultController.setText(text);
        resultController.setStage(resultStage);

        resultStage.setScene(resultWindowScene);
        resultStage.show();
    }

    public File openAskFilePathScene() {
        FileChooser fileChooser = new FileChooser();
        return fileChooser.showOpenDialog(primaryStage);
    }

    public void showError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.show();
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}