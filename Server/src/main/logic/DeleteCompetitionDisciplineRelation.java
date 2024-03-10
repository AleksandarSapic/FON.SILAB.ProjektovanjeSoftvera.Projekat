package main.logic;

import java.sql.SQLException;
import domain.CompetitionDisciplineRelation;

public class DeleteCompetitionDisciplineRelation extends BusinessLogic {

    public DeleteCompetitionDisciplineRelation() throws SQLException {
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        databaseBroker.delete((int) object, new CompetitionDisciplineRelation());
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof Integer)) {
            throw new Exception("Objekat nije validan");
        }
    }

}
