package main.logic;

import domain.CompetitionDisciplineRelation;
import domain.Discipline;
import domain.DomainObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchCompetitionDisciplineRelation extends BusinessLogic {
    
    private final List<Discipline> disciplines;
    
    public SearchCompetitionDisciplineRelation() throws SQLException {
        disciplines = new ArrayList<>();
    }
    
    @Override
    protected void executeOperation(Object object) throws Exception {
        List<DomainObject> result = databaseBroker.getByParam(new CompetitionDisciplineRelation(), String.valueOf(object));
        for (DomainObject entity : result) {
            CompetitionDisciplineRelation relation = (CompetitionDisciplineRelation) entity;
            disciplines.add(relation.getDiscipline());
        }
    }
    
    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof Integer)) {
            throw new Exception("Objekat nije validan");
        }
    }
    
    public List<Discipline> getDisciplines() {
        return disciplines;
    }
    
}
