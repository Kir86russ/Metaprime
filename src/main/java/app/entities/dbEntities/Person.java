package app.entities.dbEntities;

import java.sql.Date;

public class Person {
    private int id;
    private String name;
    private String gender;
    private Date birth_date;

    public Person() {
    }

    public Person(int id, String name, String gender, Date birth_date) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birth_date = birth_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }
}
