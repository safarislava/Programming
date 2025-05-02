// Labels_hu.java
package ru.ifmo.se.client.gui.localization;

import java.util.ListResourceBundle;

public class Labels_hu extends ListResourceBundle {
    private final Object[][] contents = {
            {"username", "Felhasználónév"},
            {"password", "Jelszó"},
            {"enter", "Belépés"},
            {"register", "Regisztráció"},
            {"log out", "Kijelentkezés"},
            {"table", "Táblázat"},
            {"map", "Térkép"},
            {"edit", "Szerkesztés"},
            {"insert", "Beszúrás"},
            {"update", "Frissítés"},
            {"remove id", "Törlés ID alapján"},
            {"remove greater id", "Nagyobb ID-k törlése"},
            {"remove greater", "Nagyobbak törlése"},
            {"remove lower", "Kisebbek törlése"},
            {"filter", "Szűrő"},
            {"default", "Alapértelmezett"},
            {"contains name", "Név tartalmazza"},
            {"full name", "Teljes név"},
            {"execute", "Végrehajtás"},
            {"count less type", "Kevesebb típusú darabszám"},
            {"execute script", "Parancsfájl végrehajtása"},
            {"info", "Információ"},
            {"help", "Súgó"},
            {"about", "Névjegy"},
            {"id", "Azonosító"},
            {"name", "Név"},
            {"coordinate x", "X Koordináta"},
            {"coordinate y", "Y Koordináta"},
            {"creation time", "Létrehozás időpontja"},
            {"annual turnover", "Éves forgalom"},
            {"full name", "Teljes név"},
            {"employees count", "Alkalmazottak száma"},
            {"type", "Típus"},
            {"zipcode", "Irányítószám"},
            {"town x", "Város X"},
            {"town y", "Város Y"},
            {"town z", "Város Z"},
            {"creator", "Létrehozó"},
            {"wrong username", "Érvénytelen felhasználónév vagy jelszó"},
            {"username used", "A felhasználónév már foglalt"},
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