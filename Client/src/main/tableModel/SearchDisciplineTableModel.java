package main.tableModel;

import domain.TimeTable;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class SearchDisciplineTableModel extends AbstractTableModel {

    private final List<TimeTable> timeTables;

    public SearchDisciplineTableModel(List<TimeTable> timeTables) {
        this.timeTables = timeTables;
    }

    @Override
    public int getRowCount() {
        return (timeTables == null) ? 0 : timeTables.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TimeTable timeTable = timeTables.get(rowIndex);
        switch (columnIndex) {
            case 0 -> {
                return timeTable.getName();
            }
            case 1 -> {
                return timeTable.getCompetition().getName();
            }
            case 2 -> {
                return timeTable.getDiscipline().getName();
            }
            case 3 -> {
                return timeTable.getMaintenanceDate();
            }
            case 4 -> {
                return timeTable.getMaintenanceTime();
            }
            default ->
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        String[] columns = {"Runda", "Takmičenje", "Disciplina", "Datum", "Vreme"};
        return columns[column];
    }
}
