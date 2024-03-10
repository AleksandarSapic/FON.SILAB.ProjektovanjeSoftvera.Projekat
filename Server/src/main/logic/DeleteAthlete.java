package main.logic;

import java.sql.SQLException;
import domain.Athlete;

public class DeleteAthlete extends BusinessLogic {

    public DeleteAthlete() throws SQLException {
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        databaseBroker.delete((int) object, new Athlete());
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof Integer)) {
            throw new Exception("Objekat nije validan");
        }
    }

}
