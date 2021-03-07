package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.User;
import sample.jdbc.DatabaseHandler;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_Field;

    @FXML
    private PasswordField password_Field;

    @FXML
    private Button SignUpButton;

    @FXML
    private TextField phone_field;

    @FXML
    private TextField email_field;

    @FXML
    private CheckBox checkBoxMale;

    @FXML
    private CheckBox checkBoxFemale;

    @FXML
    private Button mainMenuButton;

    @FXML
    void initialize() {
        SignUpButton.setOnAction(event -> {
            signUpNewUser();
        });

        mainMenuButton.setOnAction(event -> {
            mainMenuButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/sample.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

        });

    }

    private void signUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String login = login_Field.getText();
        String password = password_Field.getText();
        String email = email_field.getText();
        String phoneNumber = phone_field.getText();
        String sex = "";

        if (checkBoxMale.isSelected())
            sex = "Мужчина";
        else
            sex = "Женщина";

        User user = new User(login, password, email, phoneNumber, sex);

        dbHandler.signUpUser(user);

    }
}
