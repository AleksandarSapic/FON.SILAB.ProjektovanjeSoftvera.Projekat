package domain;

import dto.CreateCompetitionDTO;
import dto.UpdateCompetitionDTO;
import java.io.Serializable;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Competition extends DomainObject implements Serializable {

    private int id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private CompetitionType type;

    public Competition() {
    }

    public Competition(CreateCompetitionDTO competitionDTO) {
        name = competitionDTO.getName();
        startDate = competitionDTO.getStartDate();
        endDate = competitionDTO.getEndDate();

        CompetitionType typeDTO = new CompetitionType();
        typeDTO.setId(competitionDTO.getTypeId());
        type = typeDTO;
    }

    public Competition(UpdateCompetitionDTO competitionDTO) {
        id = competitionDTO.getId();
        name = competitionDTO.getName();
        startDate = competitionDTO.getStartDate();
        endDate = competitionDTO.getEndDate();

        CompetitionType typeDTO = new CompetitionType();
        typeDTO.setId(competitionDTO.getTypeId());
        type = typeDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public CompetitionType getType() {
        return type;
    }

    public void setType(CompetitionType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Takmičenje: " + "ID = " + id + ", Naziv = " + name + ", Datum početka = " + startDate + ", Datum kraja = " + endDate + ", tip = " + type.getName();
    }

    @Override
    public String getTableName() {
        return "competition";
    }

    @Override
    public String getColumnsForSelect() {
        return "c.ID, c.Name, c.DateStart, c.DateEnd, c.TypeID, ct.Name as TypeName";
    }

    @Override
    public String getTablesForSelect() {
        return "competition c JOIN competition_type ct ON(c.TypeID = ct.ID)";
    }

    @Override
    public String getWhereConditionColumnForGetById() {
        return "c.ID";
    }

    @Override
    public String getWhereConditionColumnForGetByParam() {
        return "c.Name";
    }

    @Override
    public String getWhereConditionColumnForDelete() {
        String className = getClass().getSimpleName();
        String methodName = "getWhereConditionColumnForDelete";
        throw new UnsupportedOperationException("Deletion by ID is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public String getColumnsForInsert() {
        return "Name, DateStart, DateEnd, TypeID";
    }

    @Override
    public String getParamsForInsert() {
        return "?,?,?,?";
    }

    @Override
    public String getParamsForUpdate() {
        return "Name = ?, DateStart = ?, DateEnd = ?, TypeID = ?";
    }

    @Override
    public void setParamsForInsert(PreparedStatement statement, DomainObject domainObject) throws SQLException {
        Competition competition = (Competition) domainObject;
        statement.setString(1, competition.getName());
        statement.setDate(2, Date.valueOf(competition.getStartDate()));
        statement.setDate(3, Date.valueOf(competition.getEndDate()));
        statement.setInt(4, competition.getType().getId());
    }

    @Override
    public void setParamsForUpdate(PreparedStatement statement, DomainObject domainObject) throws SQLException {
        Competition competition = (Competition) domainObject;
        statement.setString(1, competition.getName());
        statement.setDate(2, Date.valueOf(competition.getStartDate()));
        statement.setDate(3, Date.valueOf(competition.getEndDate()));
        statement.setInt(4, competition.getType().getId());
        statement.setInt(5, competition.getId());
    }

    @Override
    public void setAutoIncrementPrimaryKey(int primaryKey) {
        this.id = primaryKey;
    }

    @Override
    public DomainObject mappingObject(ResultSet result) throws SQLException {
        Competition competition = new Competition();

        competition.setId(result.getInt("ID"));
        competition.setName(result.getString("Name"));
        competition.setStartDate(result.getDate("DateStart").toLocalDate());
        competition.setEndDate(result.getDate("DateEnd").toLocalDate());

        CompetitionType type = new CompetitionType(result.getInt("TypeID"), result.getString("TypeName"));
        competition.setType(type);

        return competition;
    }

}
