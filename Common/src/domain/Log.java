package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import org.json.JSONObject;

public class Log extends DomainObject {

    private int id;
    private JSONObject beforeState;
    private JSONObject afterState;
    private LocalDate createdAt;
    private int userId;
    private int operationId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JSONObject getBeforeState() {
        return beforeState;
    }

    public void setBeforeState(JSONObject beforeState) {
        this.beforeState = beforeState;
    }

    public JSONObject getAfterState() {
        return afterState;
    }

    public void setAfterState(JSONObject afterState) {
        this.afterState = afterState;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOperationId() {
        return operationId;
    }

    public void setOperationId(int operationId) {
        this.operationId = operationId;
    }

    @Override
    public String getTableName() {
        return "log";
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
        return "beforeState, afterState, userId, operationId";
    }

    @Override
    public String getParamsForInsert() {
        return "?, ?, ?, ?";
    }

    @Override
    public String getParamsForUpdate() {
        String className = getClass().getSimpleName();
        String methodName = "getParamsForUpdate";
        throw new UnsupportedOperationException("Updating records is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public void setParamsForInsert(PreparedStatement statement, DomainObject domainObject) throws SQLException {
        Log log = (Log) domainObject;
        statement.setString(1, log.getBeforeState().toString());
        statement.setString(2, log.getAfterState().toString());
        statement.setInt(3, log.getUserId());
        statement.setInt(4, log.getOperationId());
    }

    @Override
    public void setParamsForUpdate(PreparedStatement statement, DomainObject domainObject) throws SQLException {
        String className = getClass().getSimpleName();
        String methodName = "setParamsForUpdate";
        throw new UnsupportedOperationException("Updating records is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public void setAutoIncrementPrimaryKey(int primaryKey) {
        id = primaryKey;
    }

    @Override
    public DomainObject mappingObject(ResultSet result) throws SQLException {
        String className = getClass().getSimpleName();
        String methodName = "mappingObject";
        throw new UnsupportedOperationException("ResultSet mapping is not supported for method " + methodName + " in class " + className + ".");
    }
}
