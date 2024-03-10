package main.logic;

import java.sql.SQLException;
import domain.Athlete;
import domain.DomainObject;

public class UpdateAthlete extends BusinessLogic {

    public UpdateAthlete() throws SQLException {
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        databaseBroker.update((DomainObject) object);
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof Athlete)) {
            throw new Exception("Objekat nije validan");
        }
    }

}
