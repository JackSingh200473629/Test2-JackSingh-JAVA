package com.georgiancollege.test2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class UserDetailsController implements Initializable {

    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, Integer> idColumn;

    @FXML
    private TableColumn<User, String> firstNameColumn;

    @FXML
    private TableColumn<User, String> lastNameColumn;

    @FXML
    private TableColumn<User, String> ageColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, String> phoneColumn;

    @FXML
    private TableColumn<User, String> birthDateColumn;

    @FXML
    private TableColumn<User, String> universityColumn;

    @FXML
    private Label noOfUsersLabel;

    @FXML
    private ListView<String> addressListView;

    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ApiResponse apiResponse = ApiUtility.getDataFromApi();
        System.out.println(apiResponse);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        universityColumn.setCellValueFactory(new PropertyValueFactory<>("university"));

        tableView.getItems().addAll(apiResponse.getUsers());

        int rowCount = tableView.getItems().size();
        noOfUsersLabel.setText("Number of users: " + rowCount);

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                updateAddressListView(newSelection.getAddress());
            }
        });
    }

    private void updateAddressListView(Address user) {
        addressListView.getItems().clear();
        addressListView.getItems().addAll(
                "Address: " + user.getAddress(),
                "City: " + user.getCity(),
                "Postal Code: " + user.getPostalCode(),
                "State: " + user.getState()
        );
    }

    @FXML
    void allUsersButton_onClick(ActionEvent event) {

    }

    @FXML
    void usersLessThan50Button_onClick(ActionEvent event) {

    }
}
