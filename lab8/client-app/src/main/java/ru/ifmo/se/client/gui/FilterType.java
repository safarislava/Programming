package ru.ifmo.se.client.gui;

import ru.ifmo.se.client.command.Action;

import java.util.HashMap;

public enum FilterType {
    DEFAULT,
    FILTER_CONTAINS_NAME,
    FILTER_FULL_NAME;

    public static final HashMap<FilterType, Action> filterActions = new HashMap<>(){{
       put(DEFAULT, Action.SHOW);
       put(FILTER_CONTAINS_NAME, Action.FILTER_CONTAINS_NAME);
       put(FILTER_FULL_NAME, Action.FILTER_FULL_NAME);
    }};

    public static Action getAction(FilterType type) {
        return filterActions.get(type);
    }
}
