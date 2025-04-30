module ru.ifmo.se.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens ru.ifmo.se.client to javafx.fxml;
    exports ru.ifmo.se.client;
    exports ru.ifmo.se.client.command;
    exports ru.ifmo.se.client.parser;
    exports ru.ifmo.se.client.gui;
    opens ru.ifmo.se.client.gui to javafx.fxml;
    exports ru.ifmo.se.general.command;
    exports ru.ifmo.se.general.command.assembler;
    exports ru.ifmo.se.general.contract;
    exports ru.ifmo.se.general.exeption;
    exports ru.ifmo.se.general.entity;
    exports ru.ifmo.se.general.data;
    opens ru.ifmo.se.client.parser to javafx.fxml;
    exports ru.ifmo.se.general.parser;
    opens ru.ifmo.se.general.parser to javafx.fxml;
}