package ru.ifmo.se.general.commands.builders.interfaces;

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
