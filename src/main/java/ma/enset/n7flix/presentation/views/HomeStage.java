package ma.enset.n7flix.presentation.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ma.enset.n7flix.Main;

import java.io.IOException;
import java.util.Objects;

public class HomeStage extends Stage {
    public HomeStage() throws IOException {
        Image icon = new Image(Objects.requireNonNull(getClass().getResource("/ma/enset/n7flix/Assets/Icon.png")).toExternalForm());
        this.getIcons().add(icon);
        this.initStyle(StageStyle.UNDECORATED);

        this.setTitle("Home");
        this.setScene(new AllPage());
        this.show();
    }
}
