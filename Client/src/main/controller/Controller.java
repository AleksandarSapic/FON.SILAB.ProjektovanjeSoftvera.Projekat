package main.controller;

import communication.Operation;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import dto.CreateApplicationFormDTO;
import dto.CreateAthleteDTO;
import dto.CreateCompetitionDTO;
import dto.CreateTimeTableDTO;
import dto.LoginDTO;
import dto.UpdateAthleteDTO;
import dto.UpdateCompetitionDTO;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import main.validator.IValidator;
import main.validator.util.ValidatorResult;

public class Controller implements IController {

    private IValidator validator;
    private final Socket socket;
    private final Sender sender;
    private final Receiver receiver;

    public Controller() throws IOException {
        socket = new Socket("localhost", 9000);
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    @Override
    public void setValidator(IValidator validator) {
        this.validator = validator;
    }

    @Override
    public Response addAthlete(String firstName, String lastName, String day, String month, String year, String state) {
        CreateAthleteDTO athlete = new CreateAthleteDTO();
        athlete.setFirstname(firstName);
        athlete.setLastname(lastName);
        athlete.setState(state);

        try {
            athlete.setBirthday(LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day)));
        } catch (NumberFormatException e) {
            return new Response(null, new Exception("Datum rodjenja nije u dobrom formatu!"));
        }

        ValidatorResult validate = validator.validate(athlete);

        if (validate.isSuccess()) {
            try {
                Request request = new Request(Operation.ADD_ATHLETE, athlete);
                sender.send(request);
                return (Response) receiver.receive();
            } catch (Exception ex) {
                return new Response(null, ex);
            }
        } else {
            List<String> messages = validate.getMessages();
            String errorMessage = "";
            for (String message : messages) {
                errorMessage += message;
                errorMessage += "\n";
            }
            Exception exception = new Exception(errorMessage);
            return new Response(null, exception);
        }
    }

    @Override
    public Response searchAthletes(String param) {
        try {
            Request request = new Request(Operation.GET_ATHLETE_BY_PARAM, param);
            sender.send(request);
            return (Response) receiver.receive();
        } catch (Exception ex) {
            return new Response(null, ex);
        }
    }

    @Override
    public Response updateAthlete(String id, String firstName, String lastName, String day, String month, String year, String state) {
        UpdateAthleteDTO athlete = new UpdateAthleteDTO();
        athlete.setFirstname(firstName);
        athlete.setLastname(lastName);
        athlete.setState(state);
        
        try {
            athlete.setId(Integer.parseInt(id));
            athlete.setBirthday(LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day)));
        } catch (NumberFormatException e) {
            return new Response(null, new Exception("Datum rodjenja ili ID atleticara nije u dobrom formatu!"));
        }

        ValidatorResult validate = validator.validate(athlete);
        
        if (validate.isSuccess()) {
            try {
                Request request = new Request(Operation.UPDATE_ATHLETE, athlete);
                sender.send(request);
                return (Response) receiver.receive();
            } catch (Exception ex) {
                return new Response(null, ex);
            }
        } else {
            List<String> messages = validate.getMessages();
            String errorMessage = "";
            for (String message : messages) {
                errorMessage += message;
                errorMessage += "\n";
            }
            Exception exception = new Exception(errorMessage);
            return new Response(null, exception);
        }
    }

    @Override
    public Response deleteAthlete(int id) {
        try {
            Request request = new Request(Operation.DELETE_ATHLETE, id);
            sender.send(request);
            return (Response) receiver.receive();
        } catch (Exception ex) {
            return new Response(null, ex);
        }
    }

    @Override
    public Response getAllAthletes() {
        try {
            Request request = new Request(Operation.GET_ALL_ATHLETES, null);
            sender.send(request);
            return (Response) receiver.receive();
        } catch (Exception ex) {
            return new Response(null, ex);
        }
    }

    @Override
    public Response addCompetition(String name, String dayStart, String monthStart, String yearStart, String dayEnd, String monthEnd, String yearEnd, int typeId, List<Integer> disciplines) {
        CreateCompetitionDTO competition = new CreateCompetitionDTO();
        competition.setName(name);
        
        try {
            competition.setStartDate(LocalDate.of(Integer.parseInt(yearStart), Integer.parseInt(monthStart), Integer.parseInt(dayStart)));
            competition.setEndDate(LocalDate.of(Integer.parseInt(yearEnd), Integer.parseInt(monthEnd), Integer.parseInt(dayEnd)));
        } catch (NumberFormatException e) {
            return new Response(null, new Exception("Datumi nisu u dobrom formatu!"));
        }
        
        competition.setTypeId(typeId);
        competition.setDisciplines(disciplines);
        ValidatorResult validate = validator.validate(competition);
        
        if (validate.isSuccess()) {
            try {
                Request request = new Request(Operation.ADD_COMPETITION, competition);
                sender.send(request);
                return (Response) receiver.receive();
            } catch (Exception ex) {
                return new Response(null, ex);
            }
        } else {
            List<String> messages = validate.getMessages();
            String errorMessage = "";
            for (String message : messages) {
                errorMessage += message;
                errorMessage += "\n";
            }
            Exception exception = new Exception(errorMessage);
            return new Response(null, exception);
        }
    }

    @Override
    public Response updateCompetition(int id, String name, String dayStart, String monthStart, String yearStart, String dayEnd, String monthEnd, String yearEnd, int typeId, List<Integer> disciplines) {
        UpdateCompetitionDTO competition = new UpdateCompetitionDTO();
        competition.setId(id);
        competition.setName(name);
        try {
            competition.setStartDate(LocalDate.of(Integer.parseInt(yearStart), Integer.parseInt(monthStart), Integer.parseInt(dayStart)));
            competition.setEndDate(LocalDate.of(Integer.parseInt(yearEnd), Integer.parseInt(monthEnd), Integer.parseInt(dayEnd)));
        } catch (NumberFormatException e) {
            return new Response(null, new Exception("Datumi nisu u dobrom formatu!"));
        }
        competition.setTypeId(typeId);
        competition.setDisciplines(disciplines);
        ValidatorResult validate = validator.validate(competition);
        if (validate.isSuccess()) {
            try {
                Request request = new Request(Operation.UPDATE_COMPETITION, competition);
                sender.send(request);
                return (Response) receiver.receive();
            } catch (Exception ex) {
                return new Response(null, ex);
            }
        } else {
            List<String> messages = validate.getMessages();
            String errorMessage = "";
            for (String message : messages) {
                errorMessage += message;
                errorMessage += "\n";
            }
            Exception exception = new Exception(errorMessage);
            return new Response(null, exception);
        }
    }

    @Override
    public Response searchCompetitions(String param) {
        try {
            Request request = new Request(Operation.GET_COMPETITION_BY_PARAM, param);
            sender.send(request);
            return (Response) receiver.receive();
        } catch (Exception ex) {
            return new Response(null, ex);
        }
    }

    @Override
    public Response getAllCompetitions() {
        try {
            Request request = new Request(Operation.GET_ALL_COMPETITIONS, null);
            sender.send(request);
            return (Response) receiver.receive();
        } catch (Exception ex) {
            return new Response(null, ex);
        }
    }

    @Override
    public Response getAllDisciplines() {
        try {
            Request request = new Request(Operation.GET_ALL_DISCIPLINES, null);
            sender.send(request);
            return (Response) receiver.receive();
        } catch (Exception ex) {
            return new Response(null, ex);
        }
    }

    @Override
    public Response getDisciplineByCompetition(int id) {
        try {
            Request request = new Request(Operation.GET_DISCIPLINE_BY_COMPETITION, id);
            sender.send(request);
            return (Response) receiver.receive();
        } catch (Exception ex) {
            return new Response(null, ex);
        }
    }

    @Override
    public Response addTimetable(int competitionId, int disciplineId, String name, String day, String month, String year, String hour, String minute) {
        try {
            CreateTimeTableDTO dto = new CreateTimeTableDTO();
            dto.setCompetitionId(competitionId);
            dto.setDisciplineId(disciplineId);
            dto.setName(name);

            try {
                dto.setMaintenanceDate(LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day)));
                dto.setMaintenanceTime(LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute)));
            } catch (NumberFormatException e) {
                return new Response(null, new Exception("Datum i/ili vreme nije u dobrom formatu!"));
            }

            Request request = new Request(Operation.ADD_TIMETABLE, dto);
            sender.send(request);
            return (Response) receiver.receive();
        } catch (Exception e) {
            return new Response(null, e);
        }
    }

    @Override
    public Response searchTimetables(String param) {
        try {
            Request request = new Request(Operation.GET_TIMETABLE_BY_PARAM, param);
            sender.send(request);
            return (Response) receiver.receive();
        } catch (Exception ex) {
            return new Response(null, ex);
        }
    }

    @Override
    public Response getAllTimetables() {
        try {
            Request request = new Request(Operation.GET_ALL_TIMETABLES, null);
            sender.send(request);
            return (Response) receiver.receive();
        } catch (Exception ex) {
            return new Response(null, ex);
        }
    }

    @Override
    public Response addApplicationForm(int athleteId, int competitionId, int disciplineId) {
        try {
            CreateApplicationFormDTO dto = new CreateApplicationFormDTO();
            dto.setAthleteId(athleteId);
            dto.setCompetitionId(competitionId);
            dto.setDisciplineId(disciplineId);
            dto.setPerformanceInfo("Nije još nastupio.");

            Request request = new Request(Operation.ADD_APPLICATION_FORM, dto);
            sender.send(request);
            return (Response) receiver.receive();
        } catch (Exception e) {
            return new Response(null, e);
        }
    }

    @Override
    public Response getApplicationFormsByAthlete(int id) {
        try {
            Request request = new Request(Operation.GET_APPLICATION_FORM_BY_ATHLETE, id);
            sender.send(request);
            return (Response) receiver.receive();
        } catch (Exception ex) {
            return new Response(null, ex);
        }
    }

    @Override
    public Response getAllCompetitionTypes() {
        try {
            Request request = new Request(Operation.GET_ALL_COMPETITION_TYPES, null);
            sender.send(request);
            return (Response) receiver.receive();
        } catch (Exception ex) {
            return new Response(null, ex);
        }
    }

    @Override
    public Response login(String username, String password) {
        try {
            Request request = new Request(Operation.LOGIN, new LoginDTO(username, password));
            sender.send(request);
            return (Response) receiver.receive();
        } catch (Exception ex) {
            return new Response(null, ex);
        }
    }

}
