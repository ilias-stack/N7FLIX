package ma.enset.n7flix.presentation.page_controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ma.enset.n7flix.dao.FilmDaoImp;
import ma.enset.n7flix.dao.entities.Film;
import ma.enset.n7flix.presentation.views.AllPage;
import ma.enset.n7flix.presentation.views.ForYouPage;
import ma.enset.n7flix.presentation.views.WatchedPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static ma.enset.n7flix.presentation.page_controllers.ControllersHelper.navigate;

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

    @FXML
    private GridPane moviesGrid;

    private List<Film> filmsList=null;

    List<String> selectedGenres = new ArrayList<>();

    int currentPage=0;

    @FXML
    private void initialize(){

        // populate grid block
        {
            filmsList = new FilmDaoImp().getAllFilms();
            gridFiller();
        }

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
                navigate(foryouButton.getText(),minimiseButton);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        watchedButton.setOnMouseClicked(e -> {
            try {
                navigate(watchedButton.getText(),minimiseButton);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }


    private void seachEvent(String text){
        filmsList = new FilmDaoImp().getFilmsByQuery(text);
        pagination.setPageCount((int) Math.ceil(filmsList.size()/30f));
        gridFiller();
    }

    private void sort(boolean isSelected){
        if(isSelected)
            filmsList.sort(Comparator.comparingInt(Film::getReleasedYear).reversed());

        else Collections.shuffle(filmsList);

        gridFiller();

    }

    private void paginationEvent(int pageIndex){
        currentPage = pageIndex;
        gridFiller();
    }

    private void gridFiller(){
        pagination.setPageCount((int) Math.ceil(filmsList.size()/30f));

        moviesGrid.getChildren().clear();
        for (int row = 0,filmCount=30*currentPage; row < moviesGrid.getRowCount() && filmCount<filmsList.size(); row++) {
            for (int col = 0; col < moviesGrid.getColumnCount() && filmCount<filmsList.size(); col++,filmCount++) {
                Film thisFilm=filmsList.get(filmCount);
                Group imageView = ControllersHelper.createImageView(moviesGrid,thisFilm.getPosterLink(),thisFilm.getSeriesTitle());
                imageView.setOnMouseClicked(e->{
                    System.out.println(thisFilm);
                });
                moviesGrid.add(imageView,col,row);
            }
        }

    }

    private void genresFilter(){
        filmsList = ControllersHelper.sortByGenres(filmsList,selectedGenres);
        gridFiller();
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
