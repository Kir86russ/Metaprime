package app.entities.dbEntitiesTextFields;


import javafx.scene.control.TextField;

public class OrganizationTextFieldsEntity {
    private TextField id;
    private TextField name;
    private TextField address;
    private TextField phone_number;

    public OrganizationTextFieldsEntity(TextField id, TextField name, TextField address, TextField phone_number) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
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

    public TextField getAddress() {
        return address;
    }

    public void setAddress(TextField address) {
        this.address = address;
    }

    public TextField getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(TextField phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "OrganizationTextFieldsEntity{" +
                "id=" + id.getText() +
                ", name=" + name.getText() +
                ", address=" + address.getText() +
                ", phone_number=" + phone_number.getText() +
                '}';
    }
}
