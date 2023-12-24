package ma.enset.n7flix.presentation.page_controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ma.enset.n7flix.dao.entities.Film;
import ma.enset.n7flix.presentation.views.AllPage;
import ma.enset.n7flix.presentation.views.ForYouPage;
import ma.enset.n7flix.presentation.views.WatchedPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllController {
    @FXML
    private Label closeButton;

    @FXML
    private Label minimiseButton;

    @FXML
    private TextField searchField;

    @FXML
    private CheckBox sortCheck;

    @FXML
    private MenuButton genreMenu;

    @FXML
    private Pagination pagination;

    @FXML
    private Label foryouButton,watchedButton;

    List<String> selectedGenres = new ArrayList<>();

    @FXML
    private void initialize(){
        // populate genresMenu block
        {
            for (String genre : Film.majorGenres) {
                CheckMenuItem checkMenuItem = new CheckMenuItem(genre);
                checkMenuItem.setOnAction(e-> {
                    if(! checkMenuItem.isSelected()) selectedGenres.remove(genre);
                    else selectedGenres.add(genre);
                    genresFilter();
                });
                genreMenu.getItems().add(checkMenuItem);
            }

        }

        searchField.textProperty().addListener(e-> seachEvent(searchField.getText()));

        sortCheck.setOnAction(e->sort(sortCheck.isSelected()));

        pagination.currentPageIndexProperty().addListener(e->paginationEvent(pagination.getCurrentPageIndex()));

        foryouButton.setOnMouseClicked(e-> {
            try {
                navigate(foryouButton.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        watchedButton.setOnMouseClicked(e -> {
            try {
                navigate(watchedButton.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    private void seachEvent(String text){
        System.out.println("Searching for : "+text);
    }

    private void sort(boolean isSelected){
        System.out.println(isSelected);
    }

    private void paginationEvent(int pageIndex){
        System.out.println("Current page is "+pageIndex);
    }

    private void genresFilter(){
        System.out.println(selectedGenres);
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
