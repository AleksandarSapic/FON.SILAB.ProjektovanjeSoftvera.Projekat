package domain;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public abstract class DomainObject {

    public abstract String getTableName();

    public abstract String getColumnsForSelect();

    public abstract String getTablesForSelect();

    public abstract String getWhereConditionColumnForGetById();

    public abstract String getWhereConditionColumnForGetByParam();

    public abstract String getWhereConditionColumnForDelete();

    public abstract String getColumnsForInsert();

    public abstract String getParamsForInsert();

    public abstract String getParamsForUpdate();

    public abstract void setParamsForInsert(PreparedStatement statement, DomainObject domainObject) throws SQLException;

    public abstract void setParamsForUpdate(PreparedStatement statement, DomainObject domainObject) throws SQLException;

    public abstract void setAutoIncrementPrimaryKey(int primaryKey);

    public abstract DomainObject mappingObject(ResultSet result) throws SQLException;

    public boolean containsAutoIncrementPK() {
        return true;
    }
}
