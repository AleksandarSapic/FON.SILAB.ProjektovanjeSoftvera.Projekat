package main.logic;

import java.sql.SQLException;
import domain.DomainObject;
import domain.TimeTable;

public class CreateTimetable extends BusinessLogic {
    
    public CreateTimetable() throws SQLException {
    }
    
    @Override
    protected void executeOperation(Object object) throws Exception {
        databaseBroker.add((DomainObject) object);
    }
    
    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof TimeTable)) {
            throw new Exception("Objekat nije validan");
        }
    }
    
}
