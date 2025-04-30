package ru.ifmo.se.client.parser;

import ru.ifmo.se.client.App;
import ru.ifmo.se.client.gui.AskOrganizationController;
import ru.ifmo.se.client.gui.AskStringController;
import ru.ifmo.se.client.gui.AskWindowType;
import ru.ifmo.se.general.entity.*;
import ru.ifmo.se.general.exeption.ClosedWindow;
import ru.ifmo.se.general.parser.Parser;

public class GuiParser implements Parser {
    private final App app;

    public GuiParser(App app) {
        this.app = app;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public String[] getCommandArray() {
        return new String[0];
    }

    @Override
    public Organization getOrganization() {
        try {
            AskOrganizationController askOrganizationController = app.openAskOrganizationScene(AskWindowType.ORGANIZATION);
            Organization organization = askOrganizationController.isConfirmed() ? askOrganizationController.getOrganization() : null;
            askOrganizationController.resetConfirmation();
            return organization;
        } catch (Exception e) {
            throw new ClosedWindow();
        }
    }

    @Override
    public Organization getOrganizationWithId() {
        try {
            AskOrganizationController askOrganizationController = app.openAskOrganizationScene(AskWindowType.ORGANIZATION_WITH_ID);
            Organization organization = askOrganizationController.isConfirmed() ? askOrganizationController.getOrganizationWithId() : null;
            askOrganizationController.resetConfirmation();
            return organization;
        } catch (Exception e) {
            throw new ClosedWindow();
        }
    }

    @Override
    public Integer getOrganizationId() {
        try {
            AskOrganizationController askOrganizationController = app.openAskOrganizationScene(AskWindowType.ID);
            Integer id = askOrganizationController.isConfirmed() ? askOrganizationController.getOrganizationId() : null;
            askOrganizationController.resetConfirmation();
            return id;
        } catch (Exception e) {
            throw new ClosedWindow();
        }
    }

    @Override
    public OrganizationType getOrganizationType() {
        try {
            AskOrganizationController askOrganizationController = app.openAskOrganizationScene(AskWindowType.ORGANIZATION_TYPE);
            return askOrganizationController.isConfirmed() ? askOrganizationController.getOrganizationType() : null;
        } catch (Exception e) {
            throw new ClosedWindow();
        }
    }

    @Override
    public String getString(String prompt) {
        try {
            AskStringController askStringController = app.openAskStringScene(prompt);
            return askStringController.isConfirmed() ? askStringController.getString() : null;
        } catch (Exception e) {
            throw new ClosedWindow();
        }
    }
}
