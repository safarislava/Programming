package ru.ifmo.se.client.gui.localization;

import java.util.ListResourceBundle;

public class Labels_ru extends ListResourceBundle {
    private final Object[][] contents = {
            {"username", "Имя пользователя"},
            {"password", "Пароль"},
            {"enter", "Вход"},
            {"register", "Регистрация"},
            {"log out", "Выход"},
            {"table", "Таблица"},
            {"map", "Карта"},
            {"edit", "Редактировать"},
            {"insert", "Вставить"},
            {"update", "Обновить"},
            {"remove id", "Удалить Ид"},
            {"remove greater id", "Удалить большие Ид"},
            {"remove greater", "Удалить большие"},
            {"remove lower", "Удалить меньшие"},
            {"filter", "Фильтр"},
            {"default", "По умолчанию"},
            {"contains name", "Содержит в имени"},
            {"full name", "Полное название"},
            {"execute", "Выполнить"},
            {"count less type", "Количество с меньшим типом"},
            {"execute script", "Выполнить скрипт"},
            {"info", "Информация"},
            {"help", "Помощь"},
            {"about", "О нас"},
            {"id", "Ид"},
            {"name", "Название"},
            {"coordinate x", "Координата X"},
            {"coordinate y", "Координата Y"},
            {"creation time", "Время создания"},
            {"annual turnover", "Годовой оборот"},
            {"full name", "Полное название"},
            {"employees count", "Количество сотрудников"},
            {"type", "Тип"},
            {"zipcode", "Индекс"},
            {"town x", "Город X"},
            {"town y", "Город Y"},
            {"town z", "Город Z"},
            {"creator", "Создатель"},
            {"wrong username", "Неверное имя или пароль"},
            {"username used", "Это имя уже используется"},
            {"set ru", "Установить RU"},
            {"set en", "Установить EN"},
            {"set ge", "Установить GE"},
            {"set hu", "Установить HU"}
    };


    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
