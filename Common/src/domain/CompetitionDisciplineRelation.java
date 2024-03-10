package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompetitionDisciplineRelation extends DomainObject {

    private Competition competition;
    private Discipline discipline;

    @Override
    public String toString() {
        return "Competition= " + competition.toString() + ", Discipline= " + discipline.toString();
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    @Override
    public String getTableName() {
        return "competition_discipline";
    }

    @Override
    public String getColumnsForSelect() {
        return "CompetitionID, DisciplineID, Name, CategoryID";
    }

    @Override
    public String getTablesForSelect() {
        return "competition_discipline cd JOIN discipline d ON(cd.DisciplineID = d.ID)";
    }

    @Override
    public String getWhereConditionColumnForGetById() {
        String className = getClass().getSimpleName();
        String methodName = "getWhereConditionColumnForGetById";
        throw new UnsupportedOperationException("Retrieving by ID is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public String getWhereConditionColumnForGetByParam() {
        return "CompetitionID";
    }

    @Override
    public String getWhereConditionColumnForDelete() {
        return "CompetitionID";
    }

    @Override
    public String getColumnsForInsert() {
        return "CompetitionID, DisciplineID";
    }

    @Override
    public String getParamsForInsert() {
        return "?,?";
    }

    @Override
    public String getParamsForUpdate() {
        String className = getClass().getSimpleName();
        String methodName = "getParamsForUpdate";
        throw new UnsupportedOperationException("Updating records is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public void setParamsForInsert(PreparedStatement statement, DomainObject domainObject) throws SQLException {
        CompetitionDisciplineRelation entity = (CompetitionDisciplineRelation) domainObject;
        statement.setInt(1, entity.getCompetition().getId());
        statement.setInt(2, entity.getDiscipline().getId());
    }

    @Override
    public void setParamsForUpdate(PreparedStatement statement, DomainObject domainObject) throws SQLException {
        String className = getClass().getSimpleName();
        String methodName = "setParamsForUpdate";
        throw new UnsupportedOperationException("Updating records is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public boolean containsAutoIncrementPK() {
        return false;
    }

    @Override
    public void setAutoIncrementPrimaryKey(int primaryKey) {
        String className = getClass().getSimpleName();
        String methodName = "setAutoIncrementPrimaryKey";
        throw new UnsupportedOperationException("Setting auto-incremented primary keys manually is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public DomainObject mappingObject(ResultSet result) throws SQLException {
        CompetitionDisciplineRelation entity = new CompetitionDisciplineRelation();

        Competition entityCompetition = new Competition();
        entityCompetition.setId(result.getInt("CompetitionID"));
        entity.setCompetition(entityCompetition);

        Discipline entityDiscipline = new Discipline(result.getInt("DisciplineID"), result.getString("Name"), new DisciplineCategory(result.getInt("CategoryID"), null));
        entity.setDiscipline(entityDiscipline);
        return entity;
    }

}
