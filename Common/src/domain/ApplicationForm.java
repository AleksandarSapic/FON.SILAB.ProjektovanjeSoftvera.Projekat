package domain;

import dto.CreateApplicationFormDTO;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationForm extends DomainObject implements Serializable {

    private Athlete athlete;
    private Competition competition;
    private Discipline discipline;
    private String performanceInfo;

    public ApplicationForm() {
    }

    public ApplicationForm(CreateApplicationFormDTO applicationFormDTO) {
        Athlete athleteDTO = new Athlete();
        athleteDTO.setId(applicationFormDTO.getAthleteId());
        athlete = athleteDTO;

        Discipline disciplineDTO = new Discipline();
        disciplineDTO.setId(applicationFormDTO.getDisciplineId());
        discipline = disciplineDTO;

        Competition competitionDTO = new Competition();
        competitionDTO.setId(applicationFormDTO.getCompetitionId());
        competition = competitionDTO;

        performanceInfo = applicationFormDTO.getPerformanceInfo();
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
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

    public String getPerformanceInfo() {
        return performanceInfo;
    }

    public void setPerformanceInfo(String performanceInfo) {
        this.performanceInfo = performanceInfo;
    }

    @Override
    public String toString() {
        return "Prijava: " + ", " + athlete.toString() + ", " + competition.toString() + ", " + discipline.toString() + ", Ostvaren rezultat = " + performanceInfo;
    }

    @Override
    public String getTableName() {
        return "application_form";
    }

    @Override
    public String getColumnsForSelect() {
        return "af.CompetitionID, c.Name as CompetitionName, af.DisciplineID, d.Name as DisciplineName, af.Performance";
    }

    @Override
    public String getTablesForSelect() {
        return "application_form af JOIN competition c ON(af.CompetitionID = c.ID) JOIN discipline d ON (af.DisciplineID = d.ID) JOIN athlete a ON(af.AthleteID = a.ID)";
    }

    @Override
    public String getWhereConditionColumnForGetById() {
        String className = getClass().getSimpleName();
        String methodName = "getWhereConditionColumnForGetById";
        throw new UnsupportedOperationException("Retrieving by ID is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public String getWhereConditionColumnForGetByParam() {
        return "af.AthleteID";
    }

    @Override
    public String getWhereConditionColumnForDelete() {
        String className = getClass().getSimpleName();
        String methodName = "getWhereConditionColumnForDelete";
        throw new UnsupportedOperationException("Deletion by ID is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public String getColumnsForInsert() {
        return "AthleteID, CompetitionID, DisciplineID, Performance";
    }

    @Override
    public String getParamsForInsert() {
        return "?,?,?,?";
    }

    @Override
    public String getParamsForUpdate() {
        String className = getClass().getSimpleName();
        String methodName = "getParamsForUpdate";
        throw new UnsupportedOperationException("Updating records is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public void setParamsForInsert(PreparedStatement statement, DomainObject domainObject) throws SQLException {
        ApplicationForm applicationForm = (ApplicationForm) domainObject;
        statement.setInt(1, applicationForm.getAthlete().getId());
        statement.setInt(2, applicationForm.getCompetition().getId());
        statement.setInt(3, applicationForm.getDiscipline().getId());
        statement.setString(4, applicationForm.getPerformanceInfo());
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
        ApplicationForm form = new ApplicationForm();

        Competition competition = new Competition();
        competition.setId(result.getInt("CompetitionID"));
        competition.setName(result.getString("CompetitionName"));
        form.setCompetition(competition);

        Discipline discipline = new Discipline();
        discipline.setId(result.getInt("DisciplineID"));
        discipline.setName(result.getString("DisciplineName"));
        form.setDiscipline(discipline);

        form.setPerformanceInfo(result.getString("Performance"));

        return form;
    }

}
