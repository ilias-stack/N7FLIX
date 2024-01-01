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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ma.enset.n7flix.Main;
import ma.enset.n7flix.dao.FilmDaoImp;
import ma.enset.n7flix.dao.RatingDaoImpl;
import ma.enset.n7flix.dao.entities.Film;
import ma.enset.n7flix.presentation.views.AllPage;
import ma.enset.n7flix.presentation.views.ForYouPage;
import ma.enset.n7flix.presentation.views.RatingPage;
import ma.enset.n7flix.presentation.views.WatchedPage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static ma.enset.n7flix.presentation.page_controllers.ControllersHelper.navigate;

public class WatchedController {
    @FXML
    private Label closeButton;

    @FXML
    private Label minimiseButton;

    @FXML
    private Label foryouButton,allButton;

    @FXML
    private ListView<Node> filmList;

    @FXML
    private void initialize(){
        filmList.getItems().clear();
        //navigation buttons block
        {
            allButton.setOnMouseClicked(e -> {
                try {
                    navigate(allButton.getText(), minimiseButton);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            foryouButton.setOnMouseClicked(e -> {
                try {
                    navigate(foryouButton.getText(), minimiseButton);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }

        // listing all rated films
        List<Film> ratedFilms = new FilmDaoImp().getWatchedFilms(Main.currentUser.getId());
        {
            if (ratedFilms.isEmpty()) {
                var label = new Label("You haven't rated any films yet.");
                label.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
                filmList.getItems().add(label);
            }

            else {
                for (int i = 0; i < ratedFilms.size(); i++) {
                    Film thisFilm = ratedFilms.get(i);

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

                    verticalContainer.getChildren().add(filmName);
                    verticalContainer.getChildren().add(detailsLabel);
                    verticalContainer.getChildren().add(ratingContainer);


                    container.getChildren().add(posterImage);
                    container.getChildren().add(verticalContainer);

                    ContextMenu contextMenu = new ContextMenu();
                    contextMenu.setStyle("-fx-background-color:#1c1c1c;");
                    MenuItem rateOption = new MenuItem("Update");
                    MenuItem deleteOption = new MenuItem("Delete");
                    contextMenu.getItems().addAll(rateOption, deleteOption);

                    rateOption.setOnAction(e->{
                        try {
                            new RatingPage(thisFilm);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });

                    deleteOption.setOnAction(e->{
                        new RatingDaoImpl().deleteRating(thisFilm.getId(), Main.currentUser.getId());
                        initialize();
                    });


                    container.setOnMouseClicked(e->{
                        if (e.getButton() == MouseButton.SECONDARY)
                            contextMenu.show(container, e.getScreenX(), e.getScreenY());

                    });

                    filmList.getItems().add(container);

                    final var hSeperator = new Separator();
                    hSeperator.setPrefHeight(3);
                    if(i!= ratedFilms.size()-1)
                        filmList.getItems().add(hSeperator);

                }

            }
        }


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
