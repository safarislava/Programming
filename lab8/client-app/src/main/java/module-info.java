module ru.ifmo.se.client {
    exports ru.ifmo.se.client.command;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires ru.ifmo.se.general;
    requires java.desktop;

    exports ru.ifmo.se.client to javafx.fxml, javafx.graphics;
    opens ru.ifmo.se.client.gui to javafx.fxml;
    exports ru.ifmo.se.client.gui;
}
