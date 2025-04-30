package ru.ifmo.se.general.command.assembler.type;

/**
 * Interface of command assembler.
 * Allow to set id for organization.
 *
 * @since 2.0
 * @author safarislava
 */
public interface CreatorRequired extends ServerRequired {
    void setCreator(String creator);
}
