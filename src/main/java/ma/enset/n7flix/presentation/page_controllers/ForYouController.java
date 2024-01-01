package ma.enset.n7flix.presentation.page_controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import ma.enset.n7flix.Main;
import ma.enset.n7flix.dao.FilmDaoImp;
import ma.enset.n7flix.presentation.views.AllPage;
import ma.enset.n7flix.presentation.views.ForYouPage;
import ma.enset.n7flix.presentation.views.WatchedPage;

import java.io.IOException;

import static ma.enset.n7flix.presentation.page_controllers.ControllersHelper.navigate;

public class ForYouController {
    @FXML
    private Label closeButton;

    @FXML
    private Label minimiseButton;

    @FXML
    private Label allButton,watchedButton;

    @FXML
    private ListView<Node> filmList;

    @FXML
    private void initialize(){

        // listing all suggested movies
        {
            if (new FilmDaoImp().getWatchedFilms(Main.currentUser.getId()).isEmpty()) {
                var label = new Label("You haven't rated any films yet.");
                label.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
                filmList.getItems().add(label);
            }
        }

        allButton.setOnMouseClicked(e-> {
            try {
                navigate(allButton.getText(),minimiseButton);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        watchedButton.setOnMouseClicked(e-> {
            try {
                navigate(watchedButton.getText(),minimiseButton);
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
