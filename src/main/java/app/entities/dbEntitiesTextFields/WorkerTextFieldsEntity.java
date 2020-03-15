package app.entities.dbEntitiesTextFields;

import javafx.scene.control.TextField;


public class WorkerTextFieldsEntity {
    private TextField id;
    private TextField id_person;
    private TextField id_organization;
    private TextField position;
    private TextField salary;
    private TextField start_date;

    public WorkerTextFieldsEntity(TextField id, TextField id_person, TextField id_organization, TextField position, TextField salary, TextField start_date) {
        this.id = id;
        this.id_person = id_person;
        this.id_organization = id_organization;
        this.position = position;
        this.salary = salary;
        this.start_date = start_date;
    }

    public TextField getId() {
        return id;
    }

    public void setId(TextField id) {
        this.id = id;
    }

    public TextField getId_person() {
        return id_person;
    }

    public void setId_person(TextField id_person) {
        this.id_person = id_person;
    }

    public TextField getId_organization() {
        return id_organization;
    }

    public void setId_organization(TextField id_organization) {
        this.id_organization = id_organization;
    }

    public TextField getPosition() {
        return position;
    }

    public void setPosition(TextField position) {
        this.position = position;
    }

    public TextField getSalary() {
        return salary;
    }

    public void setSalary(TextField salary) {
        this.salary = salary;
    }

    public TextField getStart_date() {
        return start_date;
    }

    public void setStart_date(TextField start_date) {
        this.start_date = start_date;
    }
}
