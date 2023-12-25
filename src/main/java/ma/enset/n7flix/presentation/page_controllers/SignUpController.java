package ma.enset.n7flix.presentation.page_controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ma.enset.n7flix.Main;
import ma.enset.n7flix.dao.UserDaoImpl;
import ma.enset.n7flix.dao.entities.User;
import ma.enset.n7flix.presentation.views.AlertBox;
import ma.enset.n7flix.presentation.views.HomeStage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class SignUpController {
    @FXML
    private Label closeButton;

    @FXML
    private Label minimiseButton;

    @FXML
    private TextField usernameField,emailField,passwordField,cPasswordField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button signButton;

    private User newUser;


    @FXML
    protected void closeButtonClick(){
        Platform.exit();
    }

    @FXML
    protected void minimiseButtonClick(){
        ((Stage) minimiseButton.getScene().getWindow()).setIconified(true);
    }

    @FXML
    protected void signup() throws IOException {
        try {
            Main.currentUser = new UserDaoImpl().createUser(newUser);
            ((Stage) minimiseButton.getScene().getWindow()).close();
            new HomeStage();
        } catch (SQLException e) {
            new AlertBox("Error!","Email or username already in use.");
        }
    }

    @FXML
    protected void transitionToLogIn() throws IOException {
        Stage currentStage = (Stage) minimiseButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FXML/login-view.fxml"));
        Parent root=fxmlLoader.load();
        Scene logInScene = new Scene(root);
        logInScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/ma/enset/n7flix/Styles/global.css")).toExternalForm());

        logInScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/ma/enset/n7flix/Styles/login.css")).toExternalForm());

        currentStage.setScene(logInScene);
    }

    @FXML
    protected void initialize() {
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsForButtonEnable());
        emailField.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsForButtonEnable());
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsForButtonEnable());
        cPasswordField.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsForButtonEnable());
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> checkFieldsForButtonEnable());
    }

    @FXML
    private void checkFieldsForButtonEnable(){
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String cPassword = cPasswordField.getText();
        try {
            String birthday = datePicker.getValue().toString();
            if(username.length()>2 && email.length()>10 && email.contains("@") && password.length()>7){
                if(cPassword.equals(password)) {
                    newUser=new User(null,username,email,password,birthday);
                    signButton.setDisable(false);
                }
                else signButton.setDisable(true);

            }
            else signButton.setDisable(true);

        }
        catch (Exception e){
            return;
        }


    }
}