package main.logic;

import java.sql.SQLException;
import domain.CompetitionDisciplineRelation;
import domain.DomainObject;

public class CreateCompetitionDisciplineRelation extends BusinessLogic {

    public CreateCompetitionDisciplineRelation() throws SQLException {
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        databaseBroker.add((DomainObject) object);
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof CompetitionDisciplineRelation)) {
            throw new Exception("Objekat nije validan");
        }
    }

}
