package ma.enset.n7flix.page_controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ma.enset.n7flix.Main;

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
    protected void closeButtonClick(){
        ((Stage) closeButton.getScene().getWindow()).close();
    }

    @FXML
    protected void minimiseButtonClick(){
        ((Stage) minimiseButton.getScene().getWindow()).setIconified(true);
    }

    @FXML
    protected void login(){
        System.out.println(usernameField.getText()+" "+passwordField.getText());
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
}