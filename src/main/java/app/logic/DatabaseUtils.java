package app.logic;

import app.entities.dbEntities.Organization;
import app.entities.dbEntities.Person;
import app.entities.dbEntities.Worker;
import app.entities.dbEntitiesTextFields.OrganizationTextFieldsEntity;
import app.entities.dbEntitiesTextFields.PersonTextFieldsEntity;
import app.entities.dbEntitiesTextFields.WorkerTextFieldsEntity;

import java.sql.*;

public class DatabaseUtils {

    private Connector connector = new Connector();

    public boolean deleteEntity(String table, String id) {
        if (!isExistById(table, id)) {
            return false;
        }

        Connection connection = connector.connect();
        String SQL = "delete from " + table + " where " + "'" + id + "'" + " = " + id;
        PreparedStatement p_state;

        try {
            p_state = connection.prepareStatement(SQL);
            p_state.executeUpdate();
        } catch (SQLException ex) {

            ex.printStackTrace();
        } finally {
            connector.disconnect(connection);
        }
        return true;
    }


    public boolean addEntity(String table, Object entity) {

        Connection connection = connector.connect();
        String SQL;
        PreparedStatement p_state;
        int index = 0;

        try {
            switch (table) {

                case "persons":
                    PersonTextFieldsEntity personEntity = (PersonTextFieldsEntity) entity;
                    SQL = "insert into persons values (?, ?, ?, ?)";
                    p_state = connection.prepareStatement(SQL);

                    p_state.setInt(++index, Integer.parseInt(personEntity.getId().getText()));
                    p_state.setString(++index, personEntity.getName().getText());
                    p_state.setString(++index, personEntity.getGender().getText());

                    // можем упасть при парсинге даты, ведь она МОЖЕТ быть null
                    if (!personEntity.getBirth_date().getText().equals("")) {
                        p_state.setDate(++index, Date.valueOf(personEntity.getBirth_date().getText()));
                    } else p_state.setNull(++index, Types.DATE);

                    p_state.executeUpdate();
                    return true;

                case "organizations":
                    OrganizationTextFieldsEntity organizationEntity = (OrganizationTextFieldsEntity) entity;
                    SQL = "insert into organizations values (?, ?, ?, ?)";
                    p_state = connection.prepareStatement(SQL);

                    p_state.setInt(++index, Integer.parseInt(organizationEntity.getId().getText()));
                    p_state.setString(++index, organizationEntity.getName().getText());
                    p_state.setString(++index, organizationEntity.getAddress().getText());
                    p_state.setString(++index, organizationEntity.getPhone_number().getText());
                    p_state.executeUpdate();
                    return true;

                case "workers":
                    WorkerTextFieldsEntity workerEntity = (WorkerTextFieldsEntity) entity;
                    SQL = "insert into workers values (?, ?, ?, ?, ?, ?)";
                    p_state = connection.prepareStatement(SQL);

                    if (workerEntity.getId_person().getText().equals("") || workerEntity.getId_organization().getText().equals(""))
                        return false;

                    p_state.setInt(++index, Integer.parseInt(workerEntity.getId().getText()));
                    p_state.setInt(++index, Integer.parseInt(workerEntity.getId_person().getText()));
                    p_state.setInt(++index, Integer.parseInt(workerEntity.getId_organization().getText()));
                    p_state.setString(++index, workerEntity.getPosition().getText());

                    // можем упасть при парсинге зарплаты, ведь она МОЖЕТ быть null
                    if (!workerEntity.getSalary().getText().equals("")) {
                        p_state.setInt(++index, Integer.parseInt(workerEntity.getSalary().getText()));
                    } else p_state.setNull(++index, Types.INTEGER);

                    // можем упасть при парсинге даты, ведь она МОЖЕТ быть null
                    if (!workerEntity.getStart_date().getText().equals("")) {
                        p_state.setDate(++index, Date.valueOf(workerEntity.getStart_date().getText()));
                    } else p_state.setNull(++index, Types.DATE);

                    p_state.executeUpdate();
                    return true;
            }
        } catch (IllegalArgumentException | SQLException e) {
            return false;
        } finally {
            connector.disconnect(connection);
        }
        return true;
    }


    // Получаем искомую запись и создаем из нее java-object
    public Object getEntityFromTable(String table, String id) {

        if (!isExistById(table, id)) {
            return null;
        }

        Connection connection = connector.connect();
        String SQL = "SELECT * from " + table + " where id = " + id;
        try {
            ResultSet rs = connection.createStatement().executeQuery(SQL);
            rs.next();
            switch (table) {
                case "persons":
                    Person person = new Person();
                    person.setId(rs.getInt("id"));
                    person.setName(rs.getString("name"));
                    person.setGender(rs.getString("gender"));
                    person.setBirth_date(rs.getDate("birth_date"));
                    return person;
                case "organizations":
                    Organization organization = new Organization();
                    organization.setId(rs.getInt("id"));
                    organization.setName(rs.getString("name"));
                    organization.setAddress(rs.getString("address"));
                    organization.setPhone_number(rs.getString("phone_number"));
                    return organization;
                case "workers":
                    Worker worker = new Worker();
                    worker.setId(rs.getInt("id"));
                    worker.setId_person(rs.getInt("id_person"));
                    worker.setId_organization(rs.getInt("id_organization"));
                    worker.setPosition(rs.getString("position"));
                    worker.setSalary(rs.getInt("salary"));
                    worker.setStart_date(rs.getDate("start_date"));
                    return worker;
                default:
                    return null;
            }
        } catch (SQLException ex) {
            return null;
        } finally {
            connector.disconnect(connection);
        }
    }


    private boolean isExistById(String table, String value) {
        if (value.equals("")) return false;
        Connection connection = connector.connect();
        String SQL = "select id from " + table + " where id  " + " = " + value;

        try {
            PreparedStatement p_state = connection.prepareStatement(SQL);
            ResultSet res = p_state.executeQuery();
            return res.next();
        } catch (SQLException e) {
            return false;
        } finally {
            connector.disconnect(connection);
        }
    }


}
