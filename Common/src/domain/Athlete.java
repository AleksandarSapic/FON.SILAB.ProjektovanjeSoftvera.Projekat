package domain;

import dto.CreateAthleteDTO;
import dto.UpdateAthleteDTO;
import java.io.Serializable;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Athlete extends DomainObject implements Serializable {

    private int id;
    private String firstname;
    private String lastname;
    private LocalDate birthday;
    private String state;

    public Athlete() {
    }

    public Athlete(CreateAthleteDTO athleteDTO) {
        firstname = athleteDTO.getFirstname();
        lastname = athleteDTO.getLastname();
        birthday = athleteDTO.getBirthday();
        state = athleteDTO.getState();
    }

    public Athlete(UpdateAthleteDTO athleteDTO) {
        id = athleteDTO.getId();
        firstname = athleteDTO.getFirstname();
        lastname = athleteDTO.getLastname();
        birthday = athleteDTO.getBirthday();
        state = athleteDTO.getState();
    }

    public int getId() {
        return id;
    }

    public void setId(int ID) {
        this.id = ID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Atleticar: " + "ID = " + id + ", Ime = " + firstname + ", Prezime = " + lastname + ", Datum rodjenja = " + birthday + ", Drzava = " + state;
    }

    @Override
    public String getTableName() {
        return "athlete";
    }

    @Override
    public String getColumnsForSelect() {
        return "*";
    }

    @Override
    public String getTablesForSelect() {
        return getTableName();
    }

    @Override
    public String getWhereConditionColumnForGetById() {
        return "ID";
    }

    @Override
    public String getWhereConditionColumnForGetByParam() {
        return "State";
    }

    @Override
    public String getWhereConditionColumnForDelete() {
        return "ID";
    }

    @Override
    public String getColumnsForInsert() {
        return "Firstname, Lastname, Birthday, State";
    }

    @Override
    public String getParamsForInsert() {
        return "?,?,?,?";
    }

    @Override
    public String getParamsForUpdate() {
        return "Firstname = ?, Lastname = ?, Birthday = ?, State = ?";
    }

    @Override
    public void setParamsForInsert(PreparedStatement statement, DomainObject domainObject) throws SQLException {
        Athlete athlete = (Athlete) domainObject;
        statement.setString(1, athlete.getFirstname());
        statement.setString(2, athlete.getLastname());
        statement.setDate(3, Date.valueOf(athlete.getBirthday()));
        statement.setString(4, athlete.getState());
    }

    @Override
    public void setParamsForUpdate(PreparedStatement statement, DomainObject domainObject) throws SQLException {
        Athlete athlete = (Athlete) domainObject;
        statement.setString(1, athlete.getFirstname());
        statement.setString(2, athlete.getLastname());
        statement.setDate(3, Date.valueOf(athlete.getBirthday()));
        statement.setString(4, athlete.getState());
        statement.setInt(5, athlete.getId());
    }

    @Override
    public void setAutoIncrementPrimaryKey(int primaryKey) {
        setId(primaryKey);
        System.out.println("Atleticar je dobio ID: " + primaryKey);
    }

    @Override
    public DomainObject mappingObject(ResultSet result) throws SQLException {
        Athlete athlete = new Athlete();
        athlete.setId(result.getInt("ID"));
        athlete.setFirstname(result.getString("Firstname"));
        athlete.setLastname(result.getString("Lastname"));
        athlete.setBirthday(result.getDate("Birthday").toLocalDate());
        athlete.setState(result.getString("State"));
        return athlete;
    }

}
