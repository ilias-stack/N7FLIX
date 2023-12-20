module ma.enset.n7flix {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ma.enset.n7flix to javafx.fxml;
    exports ma.enset.n7flix;
    exports ma.enset.n7flix.presentation.page_controllers;
    opens ma.enset.n7flix.presentation.page_controllers to javafx.fxml;
    exports ma.enset.n7flix.presentation.views;
    opens ma.enset.n7flix.presentation.views to javafx.fxml;
}