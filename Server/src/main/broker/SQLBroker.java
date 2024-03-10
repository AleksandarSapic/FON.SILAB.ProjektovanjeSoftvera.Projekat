package main.broker;

import domain.DomainObject;
import domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLBroker implements IDatabaseBroker {

    private final Connection connection;

    public SQLBroker(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<DomainObject> getAll(DomainObject entity) throws SQLException {
        List<DomainObject> entities = new ArrayList<>();
        try {
            String query = "SELECT " + entity.getColumnsForSelect() + " FROM " + entity.getTablesForSelect();

            System.out.println("Upit: " + query);

            //Pravljenje objekta koji je odgovoran za izvrsavanje upita
            Statement statement = connection.createStatement();

            //izvsi upit
            ResultSet result = statement.executeQuery(query);
            System.out.println("Objekti su uspesno procitani!");

            while (result.next()) {
                DomainObject mappedEntity = mappingObject(result, entity);
                entities.add(mappedEntity);
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Neuspesno citanje objekata iz baze u metodi getAll klase " + getClass().getSimpleName() + "!\n" + ex.getMessage());
            throw ex;
        }
        return entities;
    }

    @Override
    public DomainObject getById(int id, DomainObject entity) throws SQLException {
        try {
            String query = "SELECT " + entity.getColumnsForSelect() + " FROM " + entity.getTablesForSelect() + " WHERE " + entity.getWhereConditionColumnForGetById() + " = ?";

            System.out.println("Upit: " + query);

            //Pravljenje objekta koji je odgovoran za izvrsavanje upita
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            System.out.println("Objekat je uspesno procitan!");

            if (result.next()) {
                entity = mappingObject(result, entity);
            }

            statement.close();
        } catch (SQLException ex) {
            // Handle any SQL exceptions, print an error message, and rethrow the exception
            System.out.println("Neuspesno citanje objekta iz baze u metodi getById klase " + getClass().getSimpleName() + "!\n" + ex.getMessage());
            throw ex;
        }

        return entity;
    }

    @Override
    public List<DomainObject> getByParam(DomainObject entity, String param) throws SQLException {
        List<DomainObject> entities = new ArrayList<>();
        try {
            String query = "SELECT " + entity.getColumnsForSelect() + " FROM " + entity.getTablesForSelect() + " WHERE " + entity.getWhereConditionColumnForGetByParam() + " LIKE ?";

            System.out.println("Upit: " + query);

            PreparedStatement statement = connection.prepareStatement(query);
            try {
                Integer id = Integer.valueOf(param);
                statement.setInt(1, id);
            } catch (NumberFormatException ex) {
                statement.setString(1, "%" + param + "%");
            }

            ResultSet result = statement.executeQuery();
            System.out.println("Objekti su uspesno procitani!");

            while (result.next()) {
                DomainObject mappedEntity = mappingObject(result, entity);
                entities.add(mappedEntity);
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Neuspesno citanje objekata iz baze u metodi getByParam klase " + getClass().getSimpleName() + "!\n" + ex.getMessage());
            throw ex;
        }
        return entities;
    }

    @Override
    public void add(DomainObject entity) throws SQLException {
        try {
            String query = "INSERT INTO " + entity.getTableName()
                    + " (" + entity.getColumnsForInsert() + ") VALUES (" + entity.getParamsForInsert() + ")";

            System.out.println("Upit: " + query);

            //Pravljenje objekta koji je odgovoran za izvrsavanje upita
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            //postavljanje vrednosti parametara
            entity.setParamsForInsert(statement, entity);
            //izvsi upit
            statement.executeUpdate();
            System.out.println("Objekat uspesno dodat u bazu!");

            //pristup generisanom kljucu
            if (entity.containsAutoIncrementPK()) {
                ResultSet rsID = statement.getGeneratedKeys();
                if (rsID.next()) {
                    entity.setAutoIncrementPrimaryKey(rsID.getInt(1));
                }
                rsID.close();
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Neuspesno dodavanje objekta u bazu u metodi add klase " + getClass().getSimpleName() + "!\n" + ex.getMessage());
            throw ex;
        }
    }

    @Override
    public void delete(int id, DomainObject entity) throws SQLException {
        try {
            String query = "DELETE FROM " + entity.getTableName() + " WHERE " + entity.getWhereConditionColumnForDelete() + " = ?";

            System.out.println("Upit: " + query);

            //Pravljenje objekta koji je odgovoran za izvrsavanje upita
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);

            //izvsi upit
            statement.executeUpdate();
            System.out.println("Objekat je uspesno obrisan!");

            statement.close();
        } catch (SQLException ex) {
            // Handle any SQL exceptions, print an error message, and rethrow the exception
            System.out.println("Neuspesno brisanje objekta iz baze u metodi delete klase " + getClass().getSimpleName() + "!\n" + ex.getMessage());
            throw ex;
        }
    }

    @Override
    public void update(DomainObject entity) throws SQLException {
        try {
            String query = "UPDATE " + entity.getTableName()
                    + " SET " + entity.getParamsForUpdate() + " WHERE id = ?";

            System.out.println("Upit: " + query);

            PreparedStatement statement = connection.prepareStatement(query);

            entity.setParamsForUpdate(statement, entity);

            int result = statement.executeUpdate();

            if (result == 1) {
                System.out.println("Objekat uspesno azuriran!");
            } else {
                throw new SQLException();
            }

            statement.close();
        } catch (SQLException ex) {
            System.out.println("Neuspesno azuriranje objekta u bazi u metodi update klase " + getClass().getSimpleName() + "!\n" + ex.getMessage());
            throw ex;
        }
    }

    @Override
    public User isLogged(User entity) throws SQLException {
        User loggedUser = null;
        try {
            String query = "SELECT * FROM user WHERE Username = ? AND Password = ?";
            System.out.println("Upit: " + query);

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getPassword());

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                loggedUser = entity;
                loggedUser.setId(result.getInt("ID"));
                loggedUser.setFirstname(result.getString("Firstname"));
                loggedUser.setLastname(result.getString("Lastname"));
            }

            statement.close();
        } catch (SQLException ex) {
            System.out.println("Neuspesno citanje objekta iz baze u metodi isLogged klase " + getClass().getSimpleName() + "!\n" + ex.getMessage());
            throw ex;
        }
        return loggedUser;
    }

    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(SQLBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(SQLBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private DomainObject mappingObject(ResultSet result, DomainObject entity) throws SQLException {
        return entity.mappingObject(result);
    }

}
