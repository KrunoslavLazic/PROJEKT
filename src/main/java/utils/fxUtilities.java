package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class fxUtilities {

    public static void errorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setHeaderText("Error!");
        alert.setTitle("WineAmp Error");
        alert.showAndWait();
    }

    public static void infoMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,message,ButtonType.CLOSE);
        alert.setHeaderText("Successful!");
        alert.setTitle("WineAmp Registration");
        alert.showAndWait();
    }

}
