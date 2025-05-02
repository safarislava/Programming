// Labels_en.java
package ru.ifmo.se.client.gui.localization;

import java.util.ListResourceBundle;

public class Labels_en extends ListResourceBundle {
    private final Object[][] contents = {
            {"username", "Username"},
            {"password", "Password"},
            {"enter", "Login"},
            {"register", "Register"},
            {"log out", "Logout"},
            {"table", "Table"},
            {"map", "Map"},
            {"edit", "Edit"},
            {"insert", "Insert"},
            {"update", "Update"},
            {"remove id", "Remove by ID"},
            {"remove greater id", "Remove Greater IDs"},
            {"remove greater", "Remove Greater"},
            {"remove lower", "Remove Lower"},
            {"filter", "Filter"},
            {"default", "Default"},
            {"contains name", "Contains Name"},
            {"full name", "Full Name"},
            {"execute", "Execute"},
            {"count less type", "Count Less Type"},
            {"execute script", "Execute Script"},
            {"info", "Info"},
            {"help", "Help"},
            {"about", "About"},
            {"id", "ID"},
            {"name", "Name"},
            {"coordinate x", "Coordinate X"},
            {"coordinate y", "Coordinate Y"},
            {"creation time", "Creation Time"},
            {"annual turnover", "Annual Turnover"},
            {"full name", "Full Name"},
            {"employees count", "Employees Count"},
            {"type", "Type"},
            {"zipcode", "Zip Code"},
            {"town x", "Town X"},
            {"town y", "Town Y"},
            {"town z", "Town Z"},
            {"creator", "Creator"},
            {"wrong username", "Invalid username or password"},
            {"username used", "This username is already taken"},
            {"set ru", "Set RU"},
            {"set en", "Set EN"},
            {"set ge", "Set GE"},
            {"set hu", "Set HU"}
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}