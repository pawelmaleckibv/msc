package master.msc.config;


import org.hibernate.dialect.Dialect;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.LiteralType;
import org.hibernate.type.StringType;
import org.hibernate.type.descriptor.java.UUIDTypeDescriptor;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

import java.util.UUID;

public class MariaDbUniqueidentifierType extends AbstractSingleColumnStandardBasicType<UUID> implements LiteralType<UUID> {

    public static final MariaDbUniqueidentifierType INSTANCE = new MariaDbUniqueidentifierType();

    public MariaDbUniqueidentifierType() {
        super( VarcharTypeDescriptor.INSTANCE, UUIDTypeDescriptor.INSTANCE );
    }

    public String getName() {
        return "uniqueidentifier";
    }

    @Override
    protected boolean registerUnderJavaType() {
        return true;
    }

    public String objectToSQLString(UUID value, Dialect dialect) throws Exception {
        return StringType.INSTANCE.objectToSQLString( value.toString(), dialect );
    }
}
