package ma.enset.n7flix.presentation.page_controllers;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;
import ma.enset.n7flix.dao.entities.Film;
import ma.enset.n7flix.presentation.views.AllPage;
import ma.enset.n7flix.presentation.views.ForYouPage;
import ma.enset.n7flix.presentation.views.WatchedPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ControllersHelper {
    static public Group createImageView(GridPane moviesGrid, String imageUrl,String title) {
        ImageView imageView = new ImageView();
        Image image = new Image(imageUrl, true);

        Rectangle overlay = new Rectangle();
        overlay.setFill(Color.BLACK);
        overlay.setOpacity(0.7);
        overlay.setVisible(false);
        overlay.getStyleClass().add("film");


        String ellipsisTitle = title.substring(0, Math.min(title.length(), 8));

        Text filmTitle = new Text(ellipsisTitle.equals(title) ? title : ellipsisTitle+"...");
        filmTitle.setWrappingWidth(imageView.getFitWidth());
        filmTitle.setBoundsType(TextBoundsType.LOGICAL_VERTICAL_CENTER);
        filmTitle.getStyleClass().add("title");
        filmTitle.setFill(Color.WHITE);
        filmTitle.setTranslateY(20);
        filmTitle.setTranslateX(3);
        filmTitle.setVisible(false);

        Group group = new Group(imageView, overlay, filmTitle);
        // Give title effect
        {
            imageView.setOnMouseEntered(event -> {
                filmTitle.setVisible(true);
                overlay.setVisible(true);
            });

            overlay.setOnMouseEntered(event -> {
                filmTitle.setVisible(true);
                overlay.setVisible(true);
            });

            overlay.setOnMouseExited(event -> {
                overlay.setVisible(false);
                filmTitle.setVisible(false);
            });

            filmTitle.setOnMouseEntered(event -> {
                filmTitle.setVisible(true);
                overlay.setVisible(true);
            });

            filmTitle.setOnMouseExited(event -> {
                overlay.setVisible(false);
                filmTitle.setVisible(false);
            });
        }

        image.progressProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() == 1.0) {
                imageView.setFitWidth(moviesGrid.getWidth() / moviesGrid.getColumnCount());
                imageView.setFitHeight(moviesGrid.getHeight() / moviesGrid.getRowCount());
                overlay.setWidth(imageView.getFitWidth());
                overlay.setHeight(imageView.getFitHeight());
            }
        });

        image.errorProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
                imageView.setImage(new Image(Objects.requireNonNull(ControllersHelper.class.getResource("/ma/enset/n7flix/Assets/placeholder.jpg")).toExternalForm()));
        });

        overlay.setWidth(imageView.getFitWidth());
        overlay.setHeight(imageView.getFitHeight());

        imageView.setImage(image);
        imageView.setPreserveRatio(true);

        return group;
    }

    static public void setImage(ImageView imageView,String imageUrl) {
        Image image = new Image(imageUrl, true);

        image.errorProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
                imageView.setImage(new Image(Objects.requireNonNull(ControllersHelper.class.getResource("/ma/enset/n7flix/Assets/placeholder.jpg")).toExternalForm()));
        });

        imageView.setImage(image);
        imageView.setPreserveRatio(true);

    }

    public static String fromMinutesToFormatted(int length){
        String resp = null;
        int mins=length%60;
        int hours=length/60;

        if(hours==0) resp = mins+"m";

        else if (mins==0) resp= hours+"h";

        else resp = hours+"h "+mins+"m";

        return resp;
    }

    public static void navigate(String page, Node node) throws IOException {
        if(page.equals("ALL"))
            ((Stage) node.getScene().getWindow()).setScene(new AllPage());

        else if(page.equals("FOR YOU"))
            ((Stage) node.getScene().getWindow()).setScene(new ForYouPage());

        else
            ((Stage) node.getScene().getWindow()).setScene(new WatchedPage());
    }

    public static List<Film> sortByGenres(List<Film> films,List<String> genres){
        List<Film> response=new ArrayList<>();
        for (var film : films){
            boolean isValid=true;
            for (int i = 0; i < genres.size(); i++) {
                String filmGenre = film.getGenre().toUpperCase();
                if(!filmGenre.contains(genres.get(i))) {
                    isValid = false;
                    break;
                }
            }
            if(isValid) response.add(film);
        }
        return response;
    }

}
