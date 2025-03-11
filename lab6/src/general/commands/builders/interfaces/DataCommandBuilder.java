package general.commands.builders.interfaces;

import general.collection.OrganizationData;

/**
 * Interface of command builder.
 * Allow to set data access object.
 *
 * @since 1.0
 * @author safarislava
 */
public interface DataCommandBuilder extends ServerNeededCommandBuilder {
    void setData(OrganizationData data);
}
