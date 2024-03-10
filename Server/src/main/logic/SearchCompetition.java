package main.logic;

import domain.Competition;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import domain.DomainObject;

public class SearchCompetition extends BusinessLogic {

    private final List<Competition> competitions;

    public SearchCompetition() throws SQLException {
        competitions = new ArrayList<>();
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        List<DomainObject> result = databaseBroker.getByParam(new Competition(), (String) object);
        for (DomainObject entity : result) {
            competitions.add((Competition) entity);
        }
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof String)) {
            throw new Exception("Objekat nije validan");
        }
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }

}
