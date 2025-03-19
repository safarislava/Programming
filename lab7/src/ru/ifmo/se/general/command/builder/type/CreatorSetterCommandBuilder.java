package ru.ifmo.se.general.command.builder.type;

/**
 * Interface of command builder.
 * Allow to set id for organization.
 *
 * @since 2.0
 * @author safarislava
 */
public interface CreatorSetterCommandBuilder extends ServerNeededCommandBuilder {
    void setCreator(String creator);
}
