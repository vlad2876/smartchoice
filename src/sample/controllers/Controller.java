package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.User;
import sample.animations.Shake;
import sample.jdbc.DatabaseHandler;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_Field;

    @FXML
    private PasswordField password_Field;

    @FXML
    private Button SignInButton;

    @FXML
    private Button SignUpButton;

    @FXML
    private Button mainMenuButton;

    @FXML
    void initialize() {
        SignUpButton.setOnAction(event -> {
            SignUpButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/signup.fxml"));

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

        SignInButton.setOnAction(event -> {

            String loginText = login_Field.getText().trim();
            String passwordText = password_Field.getText().trim();

            DatabaseHandler dbHandler = new DatabaseHandler();
            User user = new User();
            user.setLogin(loginText);
            user.setPassword(passwordText);

            ResultSet result = dbHandler.getUser(user);

            int counter = 0;

            try {
                while (result.next()) {
                    counter++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (counter > 0) {
                SignInButton.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/view/signin.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            } else {
                Shake loginAnim = new Shake(login_Field);
                Shake passAnim = new Shake(password_Field);
                loginAnim.playAnimation();
                passAnim.playAnimation();
            }

        });


    }
}


