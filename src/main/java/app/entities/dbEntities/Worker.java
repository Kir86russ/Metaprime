package app.entities.dbEntities;

import java.sql.Date;

public class Worker {
    private int id;
    private int id_person;
    private int id_organization;
    private String position;
    private int salary;
    private Date start_date;


    public Worker(){}

    public Worker(int id, int id_person, int id_organization, String position, int salary, Date start_date) {
        this.id = id;
        this.id_person = id_person;
        this.id_organization = id_organization;
        this.position = position;
        this.salary = salary;
        this.start_date = start_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public int getId_organization() {
        return id_organization;
    }

    public void setId_organization(int id_organization) {
        this.id_organization = id_organization;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date date_start) {
        this.start_date = date_start;
    }
}
