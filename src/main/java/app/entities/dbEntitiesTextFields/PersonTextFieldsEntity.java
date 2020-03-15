package app.entities.dbEntitiesTextFields;


import javafx.scene.control.TextField;

public class PersonTextFieldsEntity {
    private TextField id;
    private TextField name;
    private TextField gender;
    private TextField birth_date;

    PersonTextFieldsEntity(){}

    public PersonTextFieldsEntity(TextField id, TextField name, TextField gender, TextField birth_date) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birth_date = birth_date;
    }

    public TextField getId() {
        return id;
    }

    public void setId(TextField id) {
        this.id = id;
    }

    public TextField getName() {
        return name;
    }

    public void setName(TextField name) {
        this.name = name;
    }

    public TextField getGender() {
        return gender;
    }

    public void setGender(TextField gender) {
        this.gender = gender;
    }

    public TextField getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(TextField birth_date) {
        this.birth_date = birth_date;
    }
}
