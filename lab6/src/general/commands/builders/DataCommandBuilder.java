package general.commands.builders;

import general.collection.OrganizationData;

public interface DataCommandBuilder extends CommandBuilder {
    void setData(OrganizationData data);
}
