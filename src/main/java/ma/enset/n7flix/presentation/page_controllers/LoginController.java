package ma.enset.n7flix.presentation.page_controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ma.enset.n7flix.Main;
import ma.enset.n7flix.dao.UserDaoImpl;
import ma.enset.n7flix.presentation.views.AlertBox;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
    @FXML
    private Label closeButton;

    @FXML
    private Label minimiseButton;

    @FXML
    private TextField usernameField;

    @FXML
    private  TextField passwordField;

    @FXML
    private Button logButton;


    @FXML
    protected void closeButtonClick(){
        ((Stage) closeButton.getScene().getWindow()).close();
    }

    @FXML
    protected void minimiseButtonClick(){
        ((Stage) minimiseButton.getScene().getWindow()).setIconified(true);
    }

    @FXML
    protected void login() throws IOException {

        if(!new UserDaoImpl().validCredentials(usernameField.getText(),passwordField.getText())) new AlertBox("Error!","Wrong username or password.");;

    }

    @FXML
    protected void transitionToSignUp() throws IOException {
        Stage currentStage = (Stage) minimiseButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FXML/signup-view.fxml"));
        Parent root=fxmlLoader.load();
        Scene signUpScene = new Scene(root);
        signUpScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/ma/enset/n7flix/Styles/global.css")).toExternalForm());

        signUpScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/ma/enset/n7flix/Styles/login.css")).toExternalForm());

        currentStage.setScene(signUpScene);
    }

    @FXML
    protected void initialize() {
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsForButtonEnable());
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsForButtonEnable());
    }

    @FXML
    private void checkFieldsForButtonEnable(){
        logButton.setDisable(usernameField.getText().length() <= 3 || passwordField.getText().length() < 8);
    }
}