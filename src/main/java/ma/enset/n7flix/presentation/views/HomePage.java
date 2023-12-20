package ma.enset.n7flix.presentation.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ma.enset.n7flix.Main;

import java.io.IOException;
import java.util.Objects;

public class HomePage extends Stage {
    public HomePage() throws IOException {
        Image icon = new Image(Objects.requireNonNull(getClass().getResource("/ma/enset/n7flix/Assets/Icon.png")).toExternalForm());
        this.getIcons().add(icon);
        this.initStyle(StageStyle.UNDECORATED);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FXML/home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/ma/enset/n7flix/Styles/global.css")).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/ma/enset/n7flix/Styles/home.css")).toExternalForm());

        Label usernameLabel=(Label) scene.lookup("#username");
        usernameLabel.setText(Main.currentUser.getUsername());

        this.setTitle("Home");
        this.setScene(scene);
        this.setAlwaysOnTop(true);
        this.show();
    }
}
