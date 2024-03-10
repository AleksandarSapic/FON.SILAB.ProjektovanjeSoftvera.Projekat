package main.logic;

import domain.User;
import java.sql.SQLException;

public class IsLogged extends BusinessLogic {

    private User user;

    public IsLogged() throws SQLException {
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        user = databaseBroker.isLogged((User) object);
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof User)) {
            throw new Exception("Objekat nije validan");
        }
    }

    public User getUser() {
        return user;
    }
}
