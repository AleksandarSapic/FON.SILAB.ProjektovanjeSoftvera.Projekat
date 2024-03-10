package dto;

import java.io.Serializable;

public class CreateApplicationFormDTO implements Serializable {

    private int athleteId;
    private int competitionId;
    private int disciplineId;
    private String performanceInfo;

    public int getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(int athleteId) {
        this.athleteId = athleteId;
    }

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

    public int getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(int disciplineId) {
        this.disciplineId = disciplineId;
    }

    public String getPerformanceInfo() {
        return performanceInfo;
    }

    public void setPerformanceInfo(String performanceInfo) {
        this.performanceInfo = performanceInfo;
    }

}
