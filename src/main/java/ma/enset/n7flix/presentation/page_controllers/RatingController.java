package ma.enset.n7flix.presentation.page_controllers;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ma.enset.n7flix.dao.entities.Film;

import java.util.Objects;

public class RatingController {
    @FXML
    private Label closeButton;

    @FXML
    private Label minimiseButton;

    @FXML
    private Label filmId;

    @FXML
    private ImageView filmPoster;

    @FXML
    private Label filmTitle;

    @FXML
    private Text descriptionText;

    @FXML
    private Label ratingLabel;

    @FXML
    private Label genresText;

    @FXML
    private Label yearLabel;

    @FXML
    private Label certificateLabel;

    @FXML
    private Label directorLabel;

    @FXML
    private Label filmLengthLabel;

    @FXML
    private Label star1Label,star2Label,star3Label,star4Label;

    @FXML
    private Slider ratingSlider;

    @FXML
    private Label indicationLabel;



    private Film currentFilm = null;

    public void initData(Film film) {
        currentFilm = film;
        filmId.setText(Integer.toString(currentFilm.getId()));
        ControllersHelper.setImage(filmPoster, currentFilm.getPosterLink());
        filmTitle.setText(currentFilm.getSeriesTitle());
        descriptionText.setText(currentFilm.getOverview());
        ratingLabel.setText(Double.toString(currentFilm.getImdbRating()));
        genresText.setText(currentFilm.getGenre());
        yearLabel.setText(Integer.toString(currentFilm.getReleasedYear()));
        certificateLabel.setText(!currentFilm.getCertificate().isEmpty() ? currentFilm.getCertificate() : "UNKNOWN");
        directorLabel.setText(currentFilm.getDirector());
        filmLengthLabel.setText(ControllersHelper.fromMinutesToFormatted(currentFilm.getRuntime()));
        star1Label.setText(currentFilm.getStar1());
        star2Label.setText(currentFilm.getStar2());
        star3Label.setText(currentFilm.getStar3());
        star4Label.setText(currentFilm.getStar4());

        ratingSlider.valueProperty().addListener((observable, oldValue, newValue)->{
            ratingSlider.setValue((int) Math.round(ratingSlider.getValue()));
            switch ((int) ratingSlider.getValue()){
                case 1:
                    indicationLabel.setText("I hate it!");
                    break;
                case 2:
                    indicationLabel.setText("I didn't like it.");
                    break;
                case 3:
                    indicationLabel.setText("It was good.");
                    break;
                case 4:
                    indicationLabel.setText("I Like it.");
                    break;
                case 5:
                    indicationLabel.setText("I am fascinated !");
                    break;
            }
        });
    }

    @FXML
    private void saveRating(){
        System.out.println(ratingSlider.getValue());
    }

    @FXML
    protected void closeButtonClick() {
        ((Stage) closeButton.getScene().getWindow()).close();
    }

    @FXML
    protected void minimiseButtonClick() {
        ((Stage) minimiseButton.getScene().getWindow()).setIconified(true);
    }

}
