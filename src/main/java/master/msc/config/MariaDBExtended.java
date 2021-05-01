package master.msc.config;

import org.hibernate.boot.model.TypeContributions;
import org.hibernate.dialect.MariaDBDialect;
import org.hibernate.service.ServiceRegistry;

public class MariaDBExtended extends MariaDBDialect {

    public void contributeTypes(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        typeContributions.contributeType(MariaDbUniqueidentifierType.INSTANCE);
    }
}
