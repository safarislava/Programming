package ru.ifmo.se.client.command;

public enum Action {
    CHECK_CONNECTION("check_connection"),
    CLEAR("clear"),
    COUNT_LESS_THAN_TYPE("count_less_than_type"),
    EXECUTE_SCRIPT("execute_script"),
    EXIT("exit"),
    FILTER_CONTAINS_NAME("filter_contains_name"),
    FILTER_FULL_NAME("filter_by_full_name"),
    HELP("help"),
    INFO("info"),
    INSERT("insert"),
    LOGIN("login"),
    REGISTER("register"),
    REMOVE_ID("remove_id"),
    REMOVE_GREATER("remove_greater"),
    REMOVE_GREATER_ID("remove_greater_id"),
    REMOVE_LOWER("remove_lower"),
    SHOW("show"),
    UPDATE("update"),
    UPDATE_FIELD("update_field"),;

    private final String name;
    Action(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }
}
