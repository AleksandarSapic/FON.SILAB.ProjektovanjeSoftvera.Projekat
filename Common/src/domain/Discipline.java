package domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Discipline extends DomainObject implements Serializable {

    private int id;
    private String name;
    private DisciplineCategory category;

    public Discipline(int id, String name, DisciplineCategory category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Discipline() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public DisciplineCategory getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Disciplina: " + "ID = " + id + ", Naziv = " + name + ", Kategorija = " + category.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Discipline otherDiscipline = (Discipline) obj;
        return Objects.equals(name, otherDiscipline.name);
    }

    @Override
    public String getTableName() {
        return "discipline";
    }

    @Override
    public String getColumnsForSelect() {
        return "d.ID, d.Name, dc.ID as CategoryID, dc.Name as CategoryName";
    }

    @Override
    public String getTablesForSelect() {
        return "discipline d JOIN discipline_category dc ON(d.CategoryID = dc.ID)";
    }

    @Override
    public String getWhereConditionColumnForGetById() {
        String className = getClass().getSimpleName();
        String methodName = "getWhereConditionColumnForGetById";
        throw new UnsupportedOperationException("Retrieving by ID is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public String getWhereConditionColumnForGetByParam() {
        return "d.Name";
    }

    @Override
    public String getWhereConditionColumnForDelete() {
        String className = getClass().getSimpleName();
        String methodName = "getWhereConditionColumnForDelete";
        throw new UnsupportedOperationException("Deletion is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public String getColumnsForInsert() {
        String className = getClass().getSimpleName();
        String methodName = "getColumnsForInsert";
        throw new UnsupportedOperationException("Insertion is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public String getParamsForInsert() {
        String className = getClass().getSimpleName();
        String methodName = "getParamsForInsert";
        throw new UnsupportedOperationException("Insertion is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public String getParamsForUpdate() {
        String className = getClass().getSimpleName();
        String methodName = "getParamsForUpdate";
        throw new UnsupportedOperationException("Updating records is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public void setParamsForInsert(PreparedStatement statement, DomainObject domainObject) throws SQLException {
        String className = getClass().getSimpleName();
        String methodName = "setParamsForInsert";
        throw new UnsupportedOperationException("Insertion is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public void setParamsForUpdate(PreparedStatement statement, DomainObject domainObject) throws SQLException {
        String className = getClass().getSimpleName();
        String methodName = "setParamsForUpdate";
        throw new UnsupportedOperationException("Updating records is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public void setAutoIncrementPrimaryKey(int primaryKey) {
        String className = getClass().getSimpleName();
        String methodName = "setAutoIncrementPrimaryKey";
        throw new UnsupportedOperationException("Setting auto-incremented primary keys manually is not supported for method " + methodName + " in class " + className + ".");
    }

    @Override
    public DomainObject mappingObject(ResultSet result) throws SQLException {
        DisciplineCategory mapperCategory = new DisciplineCategory(result.getInt("CategoryID"), result.getString("CategoryName"));
        return new Discipline(result.getInt("ID"), result.getString("Name"), mapperCategory);
    }

}
