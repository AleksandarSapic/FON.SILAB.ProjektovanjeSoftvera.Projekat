package main.logic;

import domain.CompetitionType;
import domain.DomainObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchCompetitionType extends BusinessLogic {

    private List<CompetitionType> types;

    public SearchCompetitionType() throws SQLException {
        types = new ArrayList<>();
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        List<DomainObject> result = databaseBroker.getByParam(new CompetitionType(), (String) object);
        for (DomainObject entity : result) {
            types.add((CompetitionType) entity);
        }
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof String)) {
            throw new Exception("Objekat nije validan");
        }
    }

    public List<CompetitionType> getTypes() {
        return types;
    }

}
