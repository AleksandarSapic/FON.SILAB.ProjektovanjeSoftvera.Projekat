package main.broker;

import domain.DomainObject;
import domain.User;
import java.sql.SQLException;
import java.util.List;

public interface IDatabaseBroker {

    List<DomainObject> getAll(DomainObject entity) throws SQLException;

    DomainObject getById(int id, DomainObject entity) throws SQLException;

    List<DomainObject> getByParam(DomainObject entity, String param) throws SQLException;

    void add(DomainObject entity) throws SQLException;

    void delete(int id, DomainObject entity) throws SQLException;

    void update(DomainObject entity) throws SQLException;

    User isLogged(User entity) throws SQLException;

    void commit();

    void rollback();
}
