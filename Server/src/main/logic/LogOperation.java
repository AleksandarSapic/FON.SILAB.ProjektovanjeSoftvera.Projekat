package main.logic;

import domain.DomainObject;
import domain.Log;
import java.sql.SQLException;

public class LogOperation extends BusinessLogic {

    public LogOperation() throws SQLException {
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        databaseBroker.add((DomainObject) object);
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof Log)) {
            throw new Exception("Objekat nije validan");
        }
    }

}
