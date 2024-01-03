package ma.enset.n7flix.presentation.page_controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ma.enset.n7flix.Main;
import ma.enset.n7flix.dao.FilmDaoImp;
import ma.enset.n7flix.dao.RatingDaoImpl;
import ma.enset.n7flix.dao.entities.Film;
import ma.enset.n7flix.presentation.views.AllPage;
import ma.enset.n7flix.presentation.views.ForYouPage;
import ma.enset.n7flix.presentation.views.RatingPage;
import ma.enset.n7flix.presentation.views.WatchedPage;
import ma.enset.n7flix.recommendation_algorithm.FilmRecommendation;
import ma.enset.n7flix.recommendation_algorithm.Recommender;

import java.io.IOException;
import java.util.Objects;

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
            else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        var recommendedFilms = new Recommender(Main.currentUser.getId()).getRecommendedFilms();
                        for (int i = 0; i < recommendedFilms.size(); i++) {
                            FilmRecommendation thisRecommendation = recommendedFilms.get(i);
                            Film thisFilm = thisRecommendation.film;

                            var container = new HBox();
                            var verticalContainer = new VBox();
                            container.setSpacing(5);
                            verticalContainer.setSpacing(15);

                            ImageView posterImage=new ImageView();
                            posterImage.setFitWidth(70);
                            ControllersHelper.setImage(posterImage,thisFilm.getPosterLink());

                            var filmName = new Label((i+1)+". "+thisFilm.getSeriesTitle());
                            filmName.setStyle("-fx-font-weight: bold;-fx-font-size: 15px;-fx-translate-y: 10;");

                            var detailsLabel = new Label(thisFilm.getReleasedYear()+"  "+ControllersHelper.fromMinutesToFormatted(thisFilm.getRuntime())+"  "+thisFilm.getCertificate());
                            detailsLabel.setStyle("-fx-text-fill:grey;-fx-font-size: 14px;");


                            ImageView starImage = new ImageView(new Image(Objects.requireNonNull(ControllersHelper.class.getResource("/ma/enset/n7flix/Assets/star.png")).toExternalForm()));
                            starImage.setFitWidth(12);
                            starImage.setFitHeight(12);

                            var imdbRatingLabel = new Label(Double.toString(thisFilm.getImdbRating()));
                            var ratesCount = new Label("  ("+(int) thisFilm.getNoOfVotes()+")");



                            var ratingContainer = new HBox();
                            ratingContainer.setSpacing(3);
                            ratingContainer.getChildren().add(starImage);
                            ratingContainer.getChildren().add(imdbRatingLabel);
                            ratingContainer.getChildren().add(ratesCount);

                            var filmSimilarity = new Label(thisRecommendation.cause);
                            filmSimilarity.setStyle("-fx-text-fill:#5799ef;-fx-font-weight:bold;");

                            verticalContainer.getChildren().add(filmName);
                            verticalContainer.getChildren().add(detailsLabel);
                            verticalContainer.getChildren().add(ratingContainer);
                            verticalContainer.getChildren().add(filmSimilarity);


                            container.getChildren().add(posterImage);
                            container.getChildren().add(verticalContainer);

                            filmList.getItems().add(container);

                            final var hSeperator = new Separator();
                            hSeperator.setPrefHeight(3);
                            if(i!= recommendedFilms.size()-1)
                                filmList.getItems().add(hSeperator);

                        }
                    }
                }).start();
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
