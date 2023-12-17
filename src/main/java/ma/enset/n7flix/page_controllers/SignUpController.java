package ma.enset.n7flix.page_controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ma.enset.n7flix.Main;

import java.io.IOException;
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
    protected void closeButtonClick(){
        ((Stage) closeButton.getScene().getWindow()).close();
    }

    @FXML
    protected void minimiseButtonClick(){
        ((Stage) minimiseButton.getScene().getWindow()).setIconified(true);
    }

    @FXML
    protected void signup(){
        System.out.println(usernameField.getText()+" "+emailField.getText()
                +" "+passwordField.getText()+" "+cPasswordField.getText()+" "+datePicker.getValue()
        );
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
}