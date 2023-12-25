package ma.enset.n7flix.presentation.page_controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class RatingController {
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
        ((Stage) minimiseButton.getScene().getWindow()).setIconified(true);
    }

}
