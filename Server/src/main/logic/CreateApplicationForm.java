package main.logic;

import java.sql.SQLException;
import domain.ApplicationForm;
import domain.DomainObject;

public class CreateApplicationForm extends BusinessLogic {
    
    public CreateApplicationForm() throws SQLException {
    }
    
    @Override
    protected void executeOperation(Object object) throws Exception {
        databaseBroker.add((DomainObject) object);
    }
    
    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof ApplicationForm)) {
            throw new Exception("Objekat nije validan");
        }
    }
    
}
