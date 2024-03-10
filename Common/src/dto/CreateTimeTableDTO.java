package dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class CreateTimeTableDTO implements Serializable{

    private String name;
    private LocalDate maintenanceDate;
    private LocalTime maintenanceTime;
    private int disciplineId;
    private int competitionId;

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

    public int getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(int disciplineId) {
        this.disciplineId = disciplineId;
    }

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

}
