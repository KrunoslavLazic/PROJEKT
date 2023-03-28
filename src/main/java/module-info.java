module controllers.projekt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.slf4j;


    opens controllers to javafx.fxml;
    exports controllers;
}