package main.logic;

import java.sql.SQLException;
import domain.Athlete;
import domain.DomainObject;

public class CreateAthlete extends BusinessLogic {

    public CreateAthlete() throws SQLException {
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        databaseBroker.add((DomainObject) object);
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof Athlete)) {
            throw new Exception("Objekat nije validan");
        }
    }

}
