package main.logic;

import domain.Athlete;
import java.sql.SQLException;

public class GetAthleteById extends BusinessLogic {

    Athlete athlete;

    public GetAthleteById() throws SQLException {
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        athlete = (Athlete) databaseBroker.getById((int) object, new Athlete());
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof Integer)) {
            throw new Exception("Objekat nije validan");
        }
    }

    public Athlete getAthlete() {
        return athlete;
    }

}
