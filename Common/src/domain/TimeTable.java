package domain;

import dto.CreateTimeTableDTO;
import java.io.Serializable;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class TimeTable extends DomainObject implements Serializable {

    private String name;
    private LocalDate maintenanceDate;
    private LocalTime maintenanceTime;
    private Discipline discipline;
    private Competition competition;

    public TimeTable() {
    }

    public TimeTable(CreateTimeTableDTO timeTableDTO) {
        Discipline disciplineDTO = new Discipline();
        disciplineDTO.setId(timeTableDTO.getDisciplineId());
        discipline = disciplineDTO;

        Competition competitionDTO = new Competition();
        competitionDTO.setId(timeTableDTO.getCompetitionId());
        competition = competitionDTO;

        name = timeTableDTO.getName();
        maintenanceDate = timeTableDTO.getMaintenanceDate();
        maintenanceTime = timeTableDTO.getMaintenanceTime();
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(LocalDate maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public LocalTime getMaintenanceTime() {
        return maintenanceTime;
    }

    public void setMaintenanceTime(LocalTime maintenanceTime) {
        this.maintenanceTime = maintenanceTime;
    }

    @Override
    public String toString() {
        return "Raspored: " + ", Naziv = " + name + ", Takmicenje = " + competition.getName() + ", Disciplina = " + discipline.getName() + ", Datum odrzavanja = " + maintenanceDate.toString() + ", Vreme odrzavanja = " + maintenanceTime.toString();
    }

    @Override
    public String getTableName() {
        return "time_table";
    }

    @Override
    public String getColumnsForSelect() {
        return "tt.Name, c.Name, d.Name, tt.MaintenanceDate, tt.MaintenanceTime";
    }

    @Override
    public String getTablesForSelect() {
        return "time_table tt JOIN discipline d ON(tt.DisciplineID=d.ID) JOIN competition c ON (tt.CompetitionID = c.ID)";
    }

    @Override
    public String getWhereConditionColumnForGetById() {
        String className = getClass().getSimpleName();
        String methodName = "getWhereConditionColumnForGetById";
        throw new UnsupportedOperationException("Retrieving by ID is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public String getWhereConditionColumnForGetByParam() {
        return "tt.Name";
    }

    @Override
    public String getWhereConditionColumnForDelete() {
        String className = getClass().getSimpleName();
        String methodName = "getWhereConditionColumnForDelete";
        throw new UnsupportedOperationException("Deletion is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public String getColumnsForInsert() {
        return "DisciplineID, CompetitionID, Name, MaintenanceDate, MaintenanceTime";
    }

    @Override
    public String getParamsForInsert() {
        return "?,?,?,?,?";
    }

    @Override
    public String getParamsForUpdate() {
        String className = getClass().getSimpleName();
        String methodName = "getParamsForUpdate";
        throw new UnsupportedOperationException("Updating records is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public void setParamsForInsert(PreparedStatement statement, DomainObject domainObject) throws SQLException {
        TimeTable timeTable = (TimeTable) domainObject;

        statement.setInt(1, timeTable.getDiscipline().getId());
        statement.setInt(2, timeTable.getCompetition().getId());
        statement.setString(3, timeTable.getName());
        statement.setDate(4, Date.valueOf(timeTable.getMaintenanceDate()));
        statement.setTime(5, Time.valueOf(timeTable.getMaintenanceTime()));
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
        TimeTable timeTable = new TimeTable();

        timeTable.setName(result.getString("tt.Name"));
        Competition competition = new Competition();
        competition.setName(result.getString("c.Name"));
        timeTable.setCompetition(competition);
        Discipline discipline = new Discipline();
        discipline.setName(result.getString("d.Name"));
        timeTable.setDiscipline(discipline);
        timeTable.setMaintenanceDate(result.getDate("tt.MaintenanceDate").toLocalDate());
        timeTable.setMaintenanceTime(result.getTime("tt.MaintenanceTime").toLocalTime());

        return timeTable;
    }

}
