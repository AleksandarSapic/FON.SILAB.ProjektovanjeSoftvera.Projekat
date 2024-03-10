package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends DomainObject {

    private int id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getTableName() {
        return "user";
    }

    @Override
    public String getColumnsForSelect() {
        String className = getClass().getSimpleName();
        String methodName = "getColumnsForSelect";
        throw new UnsupportedOperationException("Selecting columns is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public String getTablesForSelect() {
        String className = getClass().getSimpleName();
        String methodName = "getTablesForSelect";
        throw new UnsupportedOperationException("Selecting tables is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public String getWhereConditionColumnForGetById() {
        String className = getClass().getSimpleName();
        String methodName = "getWhereConditionColumnForGetById";
        throw new UnsupportedOperationException("Retrieving by ID is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public String getWhereConditionColumnForGetByParam() {
        String className = getClass().getSimpleName();
        String methodName = "getWhereConditionColumnForGetByParam";
        throw new UnsupportedOperationException("Retrieving by parameter is not supported for method " + methodName + " in class " + className + ".");
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
        String className = getClass().getSimpleName();
        String methodName = "mappingObject";
        throw new UnsupportedOperationException("ResultSet mapping is not supported for method " + methodName + " in class " + className + ".");
    }

}
