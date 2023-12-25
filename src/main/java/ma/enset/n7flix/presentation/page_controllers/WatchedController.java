package ma.enset.n7flix.presentation.page_controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ma.enset.n7flix.presentation.views.AllPage;
import ma.enset.n7flix.presentation.views.ForYouPage;
import ma.enset.n7flix.presentation.views.WatchedPage;

import java.io.IOException;

import static ma.enset.n7flix.presentation.page_controllers.ControllersHelper.navigate;

public class WatchedController {
    @FXML
    private Label closeButton;

    @FXML
    private Label minimiseButton;

    @FXML
    private Label foryouButton,allButton;

    @FXML
    private void initialize(){
        allButton.setOnMouseClicked(e-> {
            try {
                navigate(allButton.getText(),minimiseButton);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        foryouButton.setOnMouseClicked(e-> {
            try {
                navigate(foryouButton.getText(),minimiseButton);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    @FXML
    protected void closeButtonClick(){
        Platform.exit();
    }

    @FXML
    protected void minimiseButtonClick(){
        ((Stage) minimiseButton.getScene().getWindow()).setIconified(true);
    }

}
