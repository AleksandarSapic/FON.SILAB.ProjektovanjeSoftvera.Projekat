package domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompetitionType extends DomainObject implements Serializable {

    private int id;
    private String name;

    public CompetitionType() {
    }

    public CompetitionType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", " + "Name: " + name;
    }

    @Override
    public String getTableName() {
        return "competition_type";
    }

    @Override
    public String getColumnsForSelect() {
        return "*";
    }

    @Override
    public String getTablesForSelect() {
        return getTableName();
    }

    @Override
    public String getWhereConditionColumnForGetById() {
        String className = getClass().getSimpleName();
        String methodName = "getWhereConditionColumnForGetById";
        throw new UnsupportedOperationException("Retrieving by ID is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public String getWhereConditionColumnForGetByParam() {
        return "Name";
    }

    @Override
    public String getWhereConditionColumnForDelete() {
        String className = getClass().getSimpleName();
        String methodName = "getWhereConditionColumnForDelete";
        throw new UnsupportedOperationException("Deletion is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public String getColumnsForInsert() {
        String className = getClass().getSimpleName();
        String methodName = "getColumnsForInsert";
        throw new UnsupportedOperationException("Insertion is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public String getParamsForInsert() {
        String className = getClass().getSimpleName();
        String methodName = "getParamsForInsert";
        throw new UnsupportedOperationException("Insertion is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public String getParamsForUpdate() {
        String className = getClass().getSimpleName();
        String methodName = "getParamsForUpdate";
        throw new UnsupportedOperationException("Updating records is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public void setParamsForInsert(PreparedStatement statement, DomainObject domainObject) throws SQLException {
        String className = getClass().getSimpleName();
        String methodName = "setParamsForInsert";
        throw new UnsupportedOperationException("Insertion is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public void setParamsForUpdate(PreparedStatement statement, DomainObject domainObject) throws SQLException {
        String className = getClass().getSimpleName();
        String methodName = "setParamsForUpdate";
        throw new UnsupportedOperationException("Updating records is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public void setAutoIncrementPrimaryKey(int primaryKey) {
        String className = getClass().getSimpleName();
        String methodName = "setAutoIncrementPrimaryKey";
        throw new UnsupportedOperationException("Setting auto-incremented primary keys manually is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public DomainObject mappingObject(ResultSet result) throws SQLException {
        return new CompetitionType(result.getInt("ID"), result.getString("Name"));
    }

}
