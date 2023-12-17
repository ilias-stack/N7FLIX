package ma.enset.n7flix.page_controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private Label closeButton;

    @FXML
    private Label minimiseButton;

    @FXML
    protected void closeButtonClick(){
        ((Stage) closeButton.getScene().getWindow()).close();
    }

    @FXML
    protected void minimiseButtonClick(){
        ((Stage) closeButton.getScene().getWindow()).setIconified(true);
    }
}