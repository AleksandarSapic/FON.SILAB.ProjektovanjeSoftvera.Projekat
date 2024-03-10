package main.controller;

import java.sql.SQLException;
import java.util.List;
import domain.ApplicationForm;
import domain.Athlete;
import domain.Competition;
import domain.CompetitionDisciplineRelation;
import domain.CompetitionType;
import domain.Discipline;
import domain.Log;
import domain.TimeTable;
import domain.User;
import dto.CreateApplicationFormDTO;
import dto.CreateAthleteDTO;
import dto.CreateCompetitionDTO;
import dto.CreateTimeTableDTO;
import dto.LoginDTO;
import dto.UpdateAthleteDTO;
import dto.UpdateCompetitionDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.logic.CreateApplicationForm;
import main.logic.CreateAthlete;
import main.logic.CreateCompetition;
import main.logic.CreateCompetitionDisciplineRelation;
import main.logic.CreateTimetable;
import main.logic.DeleteAthlete;
import main.logic.DeleteCompetitionDisciplineRelation;
import main.logic.GetAthleteById;
import main.logic.LogOperation;
import main.logic.SearchAthlete;
import main.logic.SearchCompetition;
import main.logic.SearchCompetitionDisciplineRelation;
import main.logic.SearchCompetitionType;
import main.logic.SearchDiscipline;
import main.logic.SearchTimetable;
import main.logic.IsLogged;
import main.logic.SearchApplicationFormByAthlete;
import main.logic.UpdateAthlete;
import main.logic.UpdateCompetition;

public class Controller implements IController {

    @Override
    public void createAthlete(CreateAthleteDTO athleteDTO) throws Exception {
        CreateAthlete createAthlete = new CreateAthlete();
        Athlete athlete = new Athlete(athleteDTO);
        createAthlete.execute(athlete);
    }

    @Override
    public List<Athlete> searchAthlete(String param) throws Exception {
        SearchAthlete searchAthlete = new SearchAthlete();
        searchAthlete.execute(param);
        return searchAthlete.getAthletes();
    }

    @Override
    public void updateAthlete(UpdateAthleteDTO athleteDTO) throws Exception {
        UpdateAthlete updateAthlete = new UpdateAthlete();
        Athlete athlete = new Athlete(athleteDTO);
        updateAthlete.execute(athlete);
    }

    @Override
    public void deleteAthlete(int id) throws Exception {
        DeleteAthlete deleteAthlete = new DeleteAthlete();
        deleteAthlete.execute(id);
    }

    @Override
    public Athlete getAthleteById(int id) throws Exception {
        GetAthleteById getAthlete = new GetAthleteById();
        getAthlete.execute(id);
        return getAthlete.getAthlete();
    }

    @Override
    public void createCompetition(CreateCompetitionDTO competitionDTO) throws Exception {
        CreateCompetition createCompetition = new CreateCompetition();
        Competition competition = new Competition(competitionDTO);

        createCompetition.execute(competition);

        List<Integer> disciplines = competitionDTO.getDisciplines();
        CreateCompetitionDisciplineRelation createRelation = new CreateCompetitionDisciplineRelation();

        CompetitionDisciplineRelation relation = new CompetitionDisciplineRelation();
        relation.setCompetition(competition);
        for (Integer disciplineId : disciplines) {
            Discipline discipline = new Discipline();
            discipline.setId(disciplineId);
            relation.setDiscipline(discipline);
            createRelation.execute(relation);
        }
    }

    @Override
    public List<Competition> searchCompetition(String param) throws Exception {
        SearchCompetition searchCompetition = new SearchCompetition();
        searchCompetition.execute(param);
        return searchCompetition.getCompetitions();
    }

    @Override
    public void updateCompetition(UpdateCompetitionDTO competitionDTO) throws Exception {
        UpdateCompetition updateCompetition = new UpdateCompetition();
        Competition competition = new Competition(competitionDTO);

        updateCompetition.execute(competition);

        DeleteCompetitionDisciplineRelation deleteRelations = new DeleteCompetitionDisciplineRelation();
        deleteRelations.execute(competition.getId());

        List<Integer> disciplines = competitionDTO.getDisciplines();
        CreateCompetitionDisciplineRelation createRelation = new CreateCompetitionDisciplineRelation();
        CompetitionDisciplineRelation relation = new CompetitionDisciplineRelation();
        relation.setCompetition(competition);
        for (Integer disciplineId : disciplines) {
            Discipline discipline = new Discipline();
            discipline.setId(disciplineId);
            relation.setDiscipline(discipline);
            createRelation.execute(relation);
        }
    }

    @Override
    public List<Discipline> searchDiscipline(String param) throws Exception {
        SearchDiscipline searchDiscipline = new SearchDiscipline();
        searchDiscipline.execute(param);
        return searchDiscipline.getDisciplines();
    }

    @Override
    public List<Discipline> searchDisciplineByCompetition(int competitionId) throws Exception {
        SearchCompetitionDisciplineRelation searchDisciplineByCompetition = new SearchCompetitionDisciplineRelation();
        searchDisciplineByCompetition.execute(competitionId);
        return searchDisciplineByCompetition.getDisciplines();
    }

    @Override
    public void createTimetable(CreateTimeTableDTO timeTableDTO) throws Exception {
        CreateTimetable createTimetable = new CreateTimetable();
        TimeTable timeTable = new TimeTable(timeTableDTO);
        createTimetable.execute(timeTable);
    }

    @Override
    public List<TimeTable> searchTimeTable(String param) throws Exception {
        SearchTimetable searchTimetable = new SearchTimetable();
        searchTimetable.execute(param);
        return searchTimetable.getTimeTables();
    }

    @Override
    public void createApplicationForm(CreateApplicationFormDTO applicationFormDTO) throws SQLException, Exception {
        CreateApplicationForm createApplicationForm = new CreateApplicationForm();
        ApplicationForm applicationForm = new ApplicationForm(applicationFormDTO);
        createApplicationForm.execute(applicationForm);
    }

    @Override
    public List<ApplicationForm> searchApplicationFormByAthlete(int id) throws Exception {
        SearchApplicationFormByAthlete logic = new SearchApplicationFormByAthlete();
        logic.execute(id);
        return logic.getForms();
    }

    @Override
    public void logOperation(Log log) {
        try {
            LogOperation logOperation = new LogOperation();
            logOperation.execute(log);
        } catch (SQLException ex) {
            System.out.println("Greska" + ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<CompetitionType> searchCompetitionType(String param) throws Exception {
        SearchCompetitionType searchCompetitionType = new SearchCompetitionType();
        searchCompetitionType.execute(param);
        return searchCompetitionType.getTypes();
    }

    @Override
    public User login(LoginDTO login) throws Exception {
        User user = new User();
        user.setUsername(login.getUsername());
        user.setPassword(login.getPassword());
        IsLogged searchUser = new IsLogged();
        searchUser.execute(user);
        return searchUser.getUser();
    }

}
