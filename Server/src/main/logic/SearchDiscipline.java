package main.logic;

import domain.Discipline;
import domain.DomainObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchDiscipline extends BusinessLogic {

    private List<Discipline> disciplines;

    public SearchDiscipline() throws SQLException {
        disciplines = new ArrayList<>();
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        List<DomainObject> result = databaseBroker.getByParam(new Discipline(), (String) object);
        for (DomainObject entity : result) {
            disciplines.add((Discipline) entity);
        }
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof String)) {
            throw new Exception("Objekat nije validan");
        }
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

}
