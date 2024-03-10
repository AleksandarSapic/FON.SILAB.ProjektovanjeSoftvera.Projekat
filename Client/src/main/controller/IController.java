package main.controller;

import communication.Response;
import java.util.List;
import main.validator.IValidator;

public interface IController {

    void setValidator(IValidator validator);

    Response addAthlete(String firstName, String lastName, String day, String month, String year, String state);

    Response searchAthletes(String param);

    Response updateAthlete(String id, String firstName, String lastName, String day, String month, String year, String state);

    Response deleteAthlete(int id);

    Response getAllAthletes();

    Response addCompetition(String name, String dayStart, String monthStart, String yearStart, String dayEnd, String monthEnd, String yearEnd, int typeId, List<Integer> disciplines);

    Response updateCompetition(int id, String name, String dayStart, String monthStart, String yearStart, String dayEnd, String monthEnd, String yearEnd, int typeId, List<Integer> listOfSelectedDisciplinesIds);

    Response searchCompetitions(String param);

    Response getAllCompetitions();

    Response getAllDisciplines();

    Response getDisciplineByCompetition(int id);

    Response addTimetable(int competitionId, int disciplineId, String name, String day, String month, String year, String hour, String minute);

    Response searchTimetables(String param);

    Response getAllTimetables();

    Response addApplicationForm(int athleteId, int competitionId, int disciplineId);

    Response getApplicationFormsByAthlete(int id);

    Response getAllCompetitionTypes();

    Response login(String username, String password);

}
