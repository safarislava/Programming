package general.commands.builders.interfaces;

import general.collection.OrganizationData;

public interface DataCommandBuilder extends ServerNeededCommandBuilder {
    void setData(OrganizationData data);
}
