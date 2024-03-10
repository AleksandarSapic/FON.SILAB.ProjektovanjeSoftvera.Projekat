package main.logic;

import domain.ApplicationForm;
import domain.DomainObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchApplicationFormByAthlete extends BusinessLogic {
    
    private final List<ApplicationForm> forms;
    
    public SearchApplicationFormByAthlete() throws SQLException {
        forms = new ArrayList<>();
    }
    
    @Override
    protected void executeOperation(Object object) throws Exception {
        List<DomainObject> result = databaseBroker.getByParam(new ApplicationForm(), String.valueOf(object));
        for (DomainObject entity : result) {
            forms.add((ApplicationForm) entity);
        }
    }
    
    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof Integer)) {
            throw new Exception("Objekat nije validan");
        }
    }
    
    public List<ApplicationForm> getForms() {
        return forms;
    }
    
}
