package main.tableModel;

import domain.Athlete;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class SearchAthleteTableModel extends AbstractTableModel {

    private final List<Athlete> athletes;

    public SearchAthleteTableModel(List<Athlete> athletes) {
        this.athletes = athletes;
    }

    @Override
    public int getRowCount() {
        return (athletes == null) ? 0 : athletes.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Athlete athlete = athletes.get(rowIndex);
        switch (columnIndex) {
            case 0 -> {
                return athlete.getId();
            }
            case 1 -> {
                return athlete.getFirstname();
            }
            case 2 -> {
                return athlete.getLastname();
            }
            case 3 -> {
                return athlete.getBirthday();
            }
            case 4 -> {
                return athlete.getState();
            }
            default ->
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        String[] columns = {"ID", "Ime", "Prezime", "Datum rođenja", "Država"};
        return columns[column];
    }

}
