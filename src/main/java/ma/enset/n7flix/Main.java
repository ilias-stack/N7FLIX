package ma.enset.n7flix;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Image icon = new Image(Objects.requireNonNull(getClass().getResource("/ma/enset/n7flix/Assets/Icon.png")).toExternalForm());
        stage.getIcons().add(icon);
        stage.initStyle(StageStyle.UNDECORATED);


        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FXML/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/ma/enset/n7flix/Styles/global.css")).toExternalForm());

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/ma/enset/n7flix/Styles/login.css")).toExternalForm());
        stage.setTitle("N7FLIX");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}