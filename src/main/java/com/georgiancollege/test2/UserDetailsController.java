package com.georgiancollege.test2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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

    private ObservableList<User> allUsers = FXCollections.observableArrayList();
    private ObservableList<User> filteredUsers;

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

            if(newSelection != null){
                updateImageView(newSelection.getImage());
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

    private void updateImageView(String imagePath){
        Image userImage = new Image(imagePath);
        imageView.setImage(userImage);
    }

    @FXML
    void allUsersButton_onClick(ActionEvent event) {
        tableView.getSelectionModel().clearSelection();
        ApiResponse apiResponse = ApiUtility.getDataFromApi();
        tableView.getItems().addAll(apiResponse.getUsers());
        noOfUsersLabel.setText("No. of Users: " + filteredUsers.size());

    }


    @FXML
    void usersLessThan50Button_onClick(ActionEvent event) {
        filteredUsers = allUsers.stream().filter(user -> user.getAge() < 30)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        tableView.getSelectionModel().clearSelection();
        tableView.setItems(filteredUsers);
        noOfUsersLabel.setText("No. of Users: " + filteredUsers.size());
    }
}
