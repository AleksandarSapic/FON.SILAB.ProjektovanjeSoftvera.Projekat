package main.logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import domain.DomainObject;
import domain.TimeTable;

public class SearchTimetable extends BusinessLogic {

    private final List<TimeTable> timeTables;

    public SearchTimetable() throws SQLException {
        timeTables = new ArrayList<>();
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        List<DomainObject> result = databaseBroker.getByParam(new TimeTable(), (String) object);
        for (DomainObject entity : result) {
            timeTables.add((TimeTable) entity);
        }
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof String)) {
            throw new Exception("Objekat nije validan");
        }
    }

    public List<TimeTable> getTimeTables() {
        return timeTables;
    }

}
