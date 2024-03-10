package main.controller;

import domain.ApplicationForm;
import domain.Athlete;
import domain.Competition;
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
import java.sql.SQLException;
import java.util.List;

public interface IController {

    void createAthlete(CreateAthleteDTO athlete) throws Exception;

    List<Athlete> searchAthlete(String param) throws Exception;

    void updateAthlete(UpdateAthleteDTO athleteDTO) throws Exception;

    void deleteAthlete(int id) throws Exception;

    Athlete getAthleteById(int id) throws Exception;

    void createCompetition(CreateCompetitionDTO competitionDTO) throws Exception;

    List<Competition> searchCompetition(String param) throws Exception;

    void updateCompetition(UpdateCompetitionDTO competitionDTO) throws Exception;

    List<Discipline> searchDiscipline(String param) throws Exception;

    List<Discipline> searchDisciplineByCompetition(int competitionId) throws Exception;

    void createTimetable(CreateTimeTableDTO timeTableDTO) throws Exception;

    List<TimeTable> searchTimeTable(String param) throws Exception;

    void createApplicationForm(CreateApplicationFormDTO applicationFormDTO) throws SQLException, Exception;

    List<ApplicationForm> searchApplicationFormByAthlete(int id) throws Exception;

    void logOperation(Log log);

    List<CompetitionType> searchCompetitionType(String param) throws Exception;

    User login(LoginDTO login) throws Exception;
}
