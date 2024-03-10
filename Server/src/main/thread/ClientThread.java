package main.thread;

import java.sql.SQLException;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
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
import java.net.Socket;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import main.controller.Controller;
import main.controller.IController;
import main.server.Server;
import org.json.JSONObject;

public class ClientThread extends Thread {

    private final Socket clientSocket;
    private final Sender sender;
    private final Receiver receiver;
    private final IController controller;
    private User loginUser;
    private final Server server;

    public ClientThread(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        sender = new Sender(clientSocket);
        receiver = new Receiver(clientSocket);
        controller = new Controller();
        this.server = server;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request request = (Request) receiver.receive();

                switch (request.getOperation()) {
                    case LOGIN -> {
                        try {
                            LoginDTO login = (LoginDTO) request.getArgument();
                            User user = controller.login(login);

                            if (user == null) {
                                throw new Exception("Ne postoji korisnik sa prosleđenim kredincijalima.");
                            }
                            if (server.isUserLogged(user)) {
                                throw new Exception("Korisnik je već ulogovan.");
                            }
                            loginUser = user;
                            server.push(this);
                            Response response = new Response("Dobro došli " + user.getFirstname(), null);
                            sender.send(response);

                            JSONObject before = new JSONObject();
                            JSONObject after = new JSONObject();
                            after.append("id", loginUser.getId());

                            logOperation(before, after, 1);
                        } catch (Exception e) {
                            Response response = new Response(null, e);
                            sender.send(response);
                        }
                    }
                    case ADD_ATHLETE -> {
                        try {
                            CreateAthleteDTO athlete = (CreateAthleteDTO) request.getArgument();
                            controller.createAthlete(athlete);
                            Response response = new Response("Sistem je zapamtio atletičara", null);
                            sender.send(response);

                            JSONObject before = new JSONObject();
                            JSONObject after = new JSONObject();
                            after.append("firstname", athlete.getFirstname());
                            after.append("lastname", athlete.getLastname());
                            after.append("birthday", athlete.getBirthday().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
                            after.append("state", athlete.getState());

                            logOperation(before, after, 3);
                        } catch (Exception e) {
                            Response response = new Response(null, new Exception("Sistem ne može da zapamti atletičara"));
                            sender.send(response);
                        }
                    }
                    case GET_ALL_ATHLETES -> {
                        try {
                            List<Athlete> athletes = controller.searchAthlete("");
                            Response response = new Response(athletes, null);
                            sender.send(response);
                        } catch (Exception e) {
                            Response response = new Response(null, new Exception("Greška prilikom učitavanja atletičara."));
                            sender.send(response);
                        }
                    }
                    case GET_ATHLETE_BY_PARAM -> {
                        try {
                            String param = (String) request.getArgument();
                            List<Athlete> athletes = controller.searchAthlete(param);
                            Response response = new Response(athletes, null);
                            sender.send(response);
                        } catch (Exception e) {
                            Response response = new Response(null, new Exception("Greška prilikom učitavanja atletičara."));
                            sender.send(response);
                        }
                    }
                    case UPDATE_ATHLETE -> {
                        try {
                            UpdateAthleteDTO athlete = (UpdateAthleteDTO) request.getArgument();
                            Athlete oldAthlete = controller.getAthleteById(athlete.getId());

                            controller.updateAthlete(athlete);
                            Response response = new Response("Sistem je zapamtio atletičara.", null);
                            sender.send(response);

                            JSONObject before = new JSONObject();
                            before.append("id", oldAthlete.getId());
                            before.append("firstname", oldAthlete.getFirstname());
                            before.append("lastname", oldAthlete.getLastname());
                            before.append("birthday", oldAthlete.getBirthday().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
                            before.append("state", oldAthlete.getState());

                            JSONObject after = new JSONObject();
                            after.append("id", athlete.getId());
                            after.append("firstname", athlete.getFirstname());
                            after.append("lastname", athlete.getLastname());
                            after.append("birthday", athlete.getBirthday().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
                            after.append("state", athlete.getState());

                            logOperation(before, after, 4);
                        } catch (Exception e) {
                            Response response = new Response(null, new Exception("Sistem ne može da zapamtim atletičara."));
                            sender.send(response);
                        }
                    }
                    case DELETE_ATHLETE -> {
                        try {
                            int id = (int) request.getArgument();
                            Athlete athlete = controller.getAthleteById(id);
                            controller.deleteAthlete(id);
                            Response response = new Response("Sistem je obrisao atletičara.", null);
                            sender.send(response);

                            JSONObject before = new JSONObject();
                            before.append("id", athlete.getId());
                            before.append("firstname", athlete.getFirstname());
                            before.append("lastname", athlete.getLastname());
                            before.append("birthday", athlete.getBirthday().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
                            before.append("state", athlete.getState());
                            JSONObject after = new JSONObject();
                            after.append("id", id);
                            logOperation(before, after, 5);
                        } catch (Exception e) {
                            Response response = new Response(null, new Exception("Sistem ne može da obriše atletičara."));
                            sender.send(response);
                        }
                    }
                    case ADD_COMPETITION -> {
                        try {
                            CreateCompetitionDTO competition = (CreateCompetitionDTO) request.getArgument();
                            controller.createCompetition(competition);
                            Response response = new Response("Sistem je zapamtio takmičenje", null);
                            sender.send(response);

                            JSONObject before = new JSONObject();
                            JSONObject after = new JSONObject();
                            after.append("name", competition.getName());
                            after.append("startDate", competition.getStartDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
                            after.append("endDate", competition.getEndDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
                            after.append("typeId", competition.getTypeId());
                            after.append("disciplines", competition.getDisciplines());

                            logOperation(before, after, 6);
                        } catch (Exception e) {
                            Response response = new Response(null, new Exception("Sistem ne može da zapamti takmičenje"));
                            sender.send(response);
                        }
                    }
                    case UPDATE_COMPETITION -> {
                        try {
                            UpdateCompetitionDTO competition = (UpdateCompetitionDTO) request.getArgument();
                            controller.updateCompetition(competition);
                            Response response = new Response("Sistem je zapamtio takmičenje", null);
                            sender.send(response);
                        } catch (Exception e) {
                            Response response = new Response(null, new Exception("Sistem ne može da zapamti takmičenje"));
                            sender.send(response);
                        }

                    }
                    case GET_ALL_COMPETITIONS -> {
                        try {
                            List<Competition> competitions = controller.searchCompetition("");
                            Response response = new Response(competitions, null);
                            sender.send(response);
                        } catch (Exception e) {
                            Response response = new Response(null, new Exception("Greška prilikom učitavanja takmičenja."));
                            sender.send(response);
                        }

                    }
                    case GET_COMPETITION_BY_PARAM -> {
                        try {
                            String param = (String) request.getArgument();
                            List<Competition> competitions = controller.searchCompetition(param);
                            Response response = new Response(competitions, null);
                            sender.send(response);
                        } catch (Exception e) {
                            Response response = new Response(null, new Exception("Greška prilikom učitavanja takmičenja."));
                            sender.send(response);
                        }
                    }

                    case GET_DISCIPLINE_BY_COMPETITION -> {
                        try {
                            int competitionId = (int) request.getArgument();
                            List<Discipline> disciplines = controller.searchDisciplineByCompetition(competitionId);
                            Response response = new Response(disciplines, null);
                            sender.send(response);
                        } catch (Exception e) {
                            Response response = new Response(null, new Exception("Greška prilikom učitvanja disciplina za dato takmičenje."));
                            sender.send(response);
                        }
                    }
                    case ADD_TIMETABLE -> {
                        try {
                            CreateTimeTableDTO dto = (CreateTimeTableDTO) request.getArgument();
                            controller.createTimetable(dto);
                            Response response = new Response("Sistem je zapamtio raspored discipline", null);
                            sender.send(response);

                            JSONObject before = new JSONObject();
                            JSONObject after = new JSONObject();
                            after.append("name", dto.getName());
                            after.append("maintenanceDate", dto.getMaintenanceDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
                            after.append("maintenanceTime", dto.getMaintenanceTime().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.LONG)));
                            after.append("disciplineId", dto.getDisciplineId());
                            after.append("competitionId", dto.getCompetitionId());

                            logOperation(before, after, 8);
                        } catch (Exception e) {
                            Response response = new Response(null, new Exception("Sistem ne može da zapamti raspored discipline"));
                            sender.send(response);
                        }
                    }
                    case GET_ALL_TIMETABLES -> {
                        try {
                            List<TimeTable> timetables = controller.searchTimeTable("");
                            Response response = new Response(timetables, null);
                            sender.send(response);
                        } catch (Exception e) {
                            Response response = new Response(null, new Exception("Greška prilikom učitavanja rasporeda disciplina."));
                            sender.send(response);
                        }

                    }

                    case GET_TIMETABLE_BY_PARAM -> {
                        try {
                            String param = (String) request.getArgument();
                            List<TimeTable> timetables = controller.searchTimeTable(param);
                            Response response = new Response(timetables, null);
                            sender.send(response);
                        } catch (Exception e) {
                            Response response = new Response(null, new Exception("Greška prilikom učitavanja takmičenja."));
                            sender.send(response);
                        }
                    }

                    case ADD_APPLICATION_FORM -> {
                        try {
                            CreateApplicationFormDTO dto = (CreateApplicationFormDTO) request.getArgument();
                            controller.createApplicationForm(dto);
                            Response response = new Response("Sistem je zapamtio prijavnicu", null);
                            sender.send(response);

                            JSONObject before = new JSONObject();
                            JSONObject after = new JSONObject();
                            after.append("athleteId", dto.getAthleteId());
                            after.append("competitionId", dto.getCompetitionId());
                            after.append("disciplineId", dto.getDisciplineId());
                            after.append("performanceInfo", dto.getPerformanceInfo());

                            logOperation(before, after, 9);
                        } catch (SQLException ex) {
                            Response response = new Response(null, new Exception("Sistem ne može da zapamti prijavnicu\nPrijavnica je već uneta"));
                            sender.send(response);
                        } catch (Exception e) {
                            Response response = new Response(null, new Exception("Sistem ne može da zapamti prijavnicu"));
                            sender.send(response);
                        }
                    }

                    case GET_APPLICATION_FORM_BY_ATHLETE -> {
                        try {
                            int id = (int) request.getArgument();
                            List<ApplicationForm> forms = controller.searchApplicationFormByAthlete(id);
                            Response response = new Response(forms, null);
                            sender.send(response);
                        } catch (Exception e) {
                            Response response = new Response(null, new Exception("Sistem ne može da pročita prijavnice."));
                            sender.send(response);
                        }

                    }
                    case GET_ALL_COMPETITION_TYPES -> {
                        try {
                            List<CompetitionType> types = controller.searchCompetitionType("");
                            Response response = new Response(types, null);
                            sender.send(response);
                        } catch (Exception e) {
                            Response response = new Response(null, new Exception("Greška prilikom učitavanja tipa takmičenja."));
                            sender.send(response);
                        }
                    }

                    case GET_ALL_DISCIPLINES -> {
                        try {
                            List<Discipline> disciplines = controller.searchDiscipline("");
                            Response response = new Response(disciplines, null);
                            sender.send(response);
                        } catch (Exception e) {
                            Response response = new Response(null, new Exception("Greška prilikom učitavanja tipa takmičenja."));
                            sender.send(response);
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println("Greska u run metodi klase ClientThread: " + ex.getMessage());
                server.pop(this);
                return;
            }
        }

    }

    private void logOperation(JSONObject before, JSONObject after, int operationId) {
        Log log = new Log();
        log.setBeforeState(before);
        log.setAfterState(after);
        log.setUserId(loginUser.getId());
        log.setOperationId(operationId);
        controller.logOperation(log);
    }
}
