package app.grafics;

import app.entities.dbEntitiesTextFields.OrganizationTextFieldsEntity;
import app.entities.dbEntitiesTextFields.PersonTextFieldsEntity;
import app.entities.dbEntitiesTextFields.WorkerTextFieldsEntity;
import app.logic.DatabaseUtils;
import app.logic.HelpOperations;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;


public class ControllerFX {


    private DatabaseUtils databaseUtils = new DatabaseUtils();  // Дёргаем методы логики через этот экземляр

    /* FX коллекции для отображения графики (один для select, второй для шаблона insert) */
    private ObservableList observableListForSelecting = FXCollections.observableArrayList();
    private ObservableList observableListForInserting = FXCollections.observableArrayList();


    @FXML
    private ComboBox comboBoxTables;

    @FXML
    private Button searchButton, deleteButton, addButton;

    @FXML
    private TextField searchTextFieldId, deleteTextFieldId;

    @FXML
    private TableView resultFoundTableView, sampleForAddTableView;

    @FXML
    private Text textOkeyDelete, textErrorDelete, textOkeyAdd, textErrorAdd, textErrorFormat;

    @FXML
    void initialize() {
        comboBoxTables.getItems().addAll(
                "persons",
                "organizations",
                "workers"
        );


        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearText();
                if (comboBoxTables.getSelectionModel().getSelectedItem() == null) return;
                String tableName = comboBoxTables.getSelectionModel().getSelectedItem().toString();
                boolean isDeleted = databaseUtils.deleteEntity(tableName, deleteTextFieldId.getText());
                if (isDeleted) textOkeyDelete.setVisible(true);
                else textErrorDelete.setVisible(true);
            }
        });

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearText();
                if (comboBoxTables.getSelectionModel().getSelectedItem() == null) return;
                String tableName = comboBoxTables.getSelectionModel().getSelectedItem().toString();

                /* пишу смело get(0) потому что, гарантированно 1 элемент лежит в observableList,
                   не упадём с NPE никак */
                if (observableListForInserting.get(0) != null) {
                    boolean isAdded = databaseUtils.addEntity(tableName, observableListForInserting.get(0));
                    if (isAdded) textOkeyAdd.setVisible(true);
                    else textErrorAdd.setVisible(true);
                }
            }
        });

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearText();
                resultFoundTableView.getColumns().clear();
                resultFoundTableView.getItems().clear();
                if (comboBoxTables.getSelectionModel().getSelectedItem() == null) return;
                if (!HelpOperations.isNumber(searchTextFieldId.getText())) {
                    textErrorFormat.setVisible(true);
                    return;
                }
                String tableName = comboBoxTables.getSelectionModel().getSelectedItem().toString();
                observableListForSelecting.add(databaseUtils.getEntityFromTable(tableName, searchTextFieldId.getText()));
                displayFoundRecord(tableName);
            }
        });

        /* Каждый выбор таблицы из графического выпадающего списка влечёт за собой перерисовку шаблона для insert'а */
        comboBoxTables.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                renderAddPanel(newValue.toString());
            }
        });
    }


    private void displayFoundRecord(String table) {

        /* Колонка id у всех общая, поэтому сразу создаем ee*/
        TableColumn column_id = new TableColumn("id");
        column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        resultFoundTableView.getColumns().add(column_id);

        switch (table) {
            case "persons":
                TableColumn column_name = new TableColumn("name");
                column_name.setCellValueFactory(new PropertyValueFactory<>("name"));
                TableColumn column_gender = new TableColumn("gender");
                column_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
                TableColumn column_birthDate = new TableColumn("birth_date");
                column_birthDate.setCellValueFactory(new PropertyValueFactory<>("birth_date"));
                resultFoundTableView.getColumns().addAll(column_name, column_gender, column_birthDate);
                break;
            case "organizations":
                TableColumn column_orgName = new TableColumn("name");
                column_orgName.setCellValueFactory(new PropertyValueFactory<>("name"));
                TableColumn column_address = new TableColumn("address");
                column_address.setCellValueFactory(new PropertyValueFactory<>("address"));
                TableColumn column_phone = new TableColumn("phone_number");
                column_phone.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
                resultFoundTableView.getColumns().addAll(column_orgName, column_address, column_phone);
                break;
            case "workers":
                TableColumn column_idPerson = new TableColumn("id_person");
                column_idPerson.setCellValueFactory(new PropertyValueFactory<>("id_person"));
                TableColumn column_idOrg = new TableColumn("id_organization");
                column_idOrg.setCellValueFactory(new PropertyValueFactory<>("id_organization"));
                TableColumn column_position = new TableColumn("position");
                column_position.setCellValueFactory(new PropertyValueFactory<>("position"));
                TableColumn column_salary = new TableColumn("salary");
                column_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
                TableColumn column_startDate = new TableColumn("start_date");
                column_startDate.setCellValueFactory(new PropertyValueFactory<>("start_date"));
                resultFoundTableView.getColumns().addAll(column_idPerson, column_idOrg, column_position, column_salary, column_startDate);
                break;
        }
        resultFoundTableView.setItems(observableListForSelecting);
    }


    // Отрисовка шаблона для insert'a
    private void renderAddPanel(String tableName) {
        sampleForAddTableView.getColumns().clear();
        sampleForAddTableView.getItems().clear();

        /* Колонка id у всех общая, поэтому сразу создаем ee*/
        TableColumn column_id = new TableColumn("id");
        column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        column_id.setMaxWidth(50);
        sampleForAddTableView.getColumns().add(column_id);

        switch (tableName) {
            case "persons":
                TableColumn column_name = new TableColumn("name");
                column_name.setCellValueFactory(new PropertyValueFactory<>("name"));
                column_name.setMaxWidth(100);
                TableColumn column_gender = new TableColumn("gender");
                column_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
                column_gender.setMaxWidth(100);
                TableColumn column_birthDate = new TableColumn("birth_date");
                column_birthDate.setCellValueFactory(new PropertyValueFactory<>("birth_date"));
                column_birthDate.setMaxWidth(100);
                TextField textFieldBirthDay = new TextField();
                textFieldBirthDay.setPromptText("YYYY-MM-DD");
                sampleForAddTableView.getColumns().addAll(column_name, column_gender, column_birthDate);
                observableListForInserting.add(new PersonTextFieldsEntity(new TextField(), new TextField(), new TextField(),
                        textFieldBirthDay));
                break;
            case "organizations":
                TableColumn column_orgName = new TableColumn("name");
                column_orgName.setCellValueFactory(new PropertyValueFactory<>("name"));
                column_orgName.setMaxWidth(100);
                TableColumn column_address = new TableColumn("address");
                column_address.setCellValueFactory(new PropertyValueFactory<>("address"));
                column_orgName.setMaxWidth(90);
                TableColumn column_phone = new TableColumn("phone_number");
                column_phone.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
                column_orgName.setMaxWidth(90);
                sampleForAddTableView.getColumns().addAll(column_orgName, column_address, column_phone);
                observableListForInserting.add(new OrganizationTextFieldsEntity(new TextField(), new TextField(),
                        new TextField(), new TextField()));
                break;
            case "workers":
                TableColumn column_idPerson = new TableColumn("id_person");
                column_idPerson.setCellValueFactory(new PropertyValueFactory<>("id_person"));
                column_idPerson.setMaxWidth(80);
                TableColumn column_idOrg = new TableColumn("id_organization");
                column_idOrg.setCellValueFactory(new PropertyValueFactory<>("id_organization"));
                column_idOrg.setMaxWidth(100);
                TableColumn column_position = new TableColumn("position");
                column_position.setCellValueFactory(new PropertyValueFactory<>("position"));
                column_position.setMaxWidth(80);
                TableColumn column_salary = new TableColumn("salary");
                column_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
                column_salary.setMaxWidth(80);
                TableColumn column_startDate = new TableColumn("start_date");
                column_startDate.setCellValueFactory(new PropertyValueFactory<>("start_date"));
                column_startDate.setMaxWidth(100);
                TextField textFieldDate = new TextField();
                textFieldDate.setPromptText("YYYY-MM-DD");
                sampleForAddTableView.getColumns().addAll(column_idPerson, column_idOrg, column_position, column_salary, column_startDate);
                observableListForInserting.add(new WorkerTextFieldsEntity(new TextField(), new TextField(),
                        new TextField(), new TextField(), new TextField(), textFieldDate));
                break;
        }
        sampleForAddTableView.setItems(observableListForInserting);
    }

    private void clearText() {
        textOkeyDelete.setVisible(false);
        textErrorDelete.setVisible(false);
        textOkeyAdd.setVisible(false);
        textErrorAdd.setVisible(false);
        textErrorFormat.setVisible(false);
    }
}


