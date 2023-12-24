package ma.enset.n7flix.presentation.page_controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ma.enset.n7flix.presentation.views.AllPage;
import ma.enset.n7flix.presentation.views.ForYouPage;
import ma.enset.n7flix.presentation.views.WatchedPage;

import java.io.IOException;

public class ForYouController {
    @FXML
    private Label closeButton;

    @FXML
    private Label minimiseButton;

    @FXML
    private Label allButton,watchedButton;

    @FXML
    private void initialize(){
        allButton.setOnMouseClicked(e-> {
            try {
                navigate(allButton.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        watchedButton.setOnMouseClicked(e-> {
            try {
                navigate(watchedButton.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void navigate(String page) throws IOException {
        if(page.equals("ALL"))
            ((Stage) minimiseButton.getScene().getWindow()).setScene(new AllPage());

        else if(page.equals("FOR YOU"))
            ((Stage) minimiseButton.getScene().getWindow()).setScene(new ForYouPage());

        else
            ((Stage) minimiseButton.getScene().getWindow()).setScene(new WatchedPage());

    }

    @FXML
    protected void closeButtonClick(){
        ((Stage) closeButton.getScene().getWindow()).close();
    }

    @FXML
    protected void minimiseButtonClick(){
        ((Stage) minimiseButton.getScene().getWindow()).setIconified(true);
    }

}
