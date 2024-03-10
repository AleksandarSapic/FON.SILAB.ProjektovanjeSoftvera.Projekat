package dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class CreateCompetitionDTO implements Serializable{

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private int typeId;
    private List<Integer> disciplines;

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

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public List<Integer> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Integer> disciplines) {
        this.disciplines = disciplines;
    }

}
