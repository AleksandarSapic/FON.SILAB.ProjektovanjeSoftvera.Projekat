package main.tableModel;

import domain.Competition;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class SearchCompetitionTableModel extends AbstractTableModel {

    private List<Competition> competitions;

    public SearchCompetitionTableModel(List<Competition> competitions) {
        this.competitions = competitions;
    }

    @Override
    public int getRowCount() {
        return (competitions == null) ? 0 : competitions.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Competition competition = competitions.get(rowIndex);
        switch (columnIndex) {
            case 0 -> {
                return competition.getId();
            }
            case 1 -> {
                return competition.getName();
            }
            case 2 -> {
                return competition.getStartDate();
            }
            case 3 -> {
                return competition.getEndDate();
            }
            case 4 -> {
                return competition.getType().getName();
            }
            default ->
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        String[] columns = {"ID", "Naziv", "Datum početka", "Datum kraja", "Tip"};
        return columns[column];
    }
}
