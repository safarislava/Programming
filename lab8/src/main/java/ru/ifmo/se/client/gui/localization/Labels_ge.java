// Labels_ge.java
package ru.ifmo.se.client.gui.localization;

import java.util.ListResourceBundle;

public class Labels_ge extends ListResourceBundle {
    private final Object[][] contents = {
            {"username", "Benutzername"},
            {"password", "Passwort"},
            {"enter", "Einloggen"},
            {"register", "Registrieren"},
            {"log out", "Abmelden"},
            {"table", "Tabelle"},
            {"map", "Karte"},
            {"edit", "Bearbeiten"},
            {"insert", "Einfügen"},
            {"update", "Aktualisieren"},
            {"remove id", "Löschen nach ID"},
            {"remove greater id", "Größere IDs löschen"},
            {"remove greater", "Größere Elemente entfernen"},
            {"remove lower", "Kleinere Elemente entfernen"},
            {"filter", "Filter"},
            {"default", "Standard"},
            {"contains name", "Name enthält"},
            {"full name", "Vollständiger Name"},
            {"execute", "Ausführen"},
            {"count less type", "Anzahl geringerer Art"},
            {"execute script", "Skript ausführen"},
            {"info", "Information"},
            {"help", "Hilfe"},
            {"about", "Über uns"},
            {"id", "ID"},
            {"name", "Name"},
            {"coordinate x", "X-Koordinate"},
            {"coordinate y", "Y-Koordinate"},
            {"creation time", "Erstellungszeit"},
            {"annual turnover", "Jahresumsatz"},
            {"full name", "Vollständiger Name"},
            {"employees count", "Mitarbeiteranzahl"},
            {"type", "Typ"},
            {"zipcode", "Postleitzahl"},
            {"town x", "Stadt X"},
            {"town y", "Stadt Y"},
            {"town z", "Stadt Z"},
            {"creator", "Ersteller"},
            {"wrong username", "Ungültiger Benutzername oder Passwort"},
            {"username used", "Dieser Benutzername wird bereits verwendet"},
            {"set ru", "Setze RU"},
            {"set en", "Setze EN"},
            {"set ge", "Setze GE"},
            {"set hu", "Setze HU"}
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}