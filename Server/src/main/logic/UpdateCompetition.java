package main.logic;

import domain.Competition;
import java.sql.SQLException;
import domain.DomainObject;

public class UpdateCompetition extends BusinessLogic {

    public UpdateCompetition() throws SQLException {
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        databaseBroker.update((DomainObject) object);
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof Competition)) {
            throw new Exception("Objekat nije validan");
        }
    }

}
