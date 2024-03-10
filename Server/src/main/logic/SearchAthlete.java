package main.logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import domain.Athlete;
import domain.DomainObject;

public class SearchAthlete extends BusinessLogic {

    private final List<Athlete> athletes;

    public SearchAthlete() throws SQLException {
        athletes = new ArrayList<>();
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        List<DomainObject> result = databaseBroker.getByParam(new Athlete(), (String) object);
        for (DomainObject entity : result) {
            athletes.add((Athlete) entity);
        }
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof String)) {
            throw new Exception("Objekat nije validan");
        }
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }

}
