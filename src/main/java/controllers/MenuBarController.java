package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class MenuBarController {

    private LoginController userNamewtf;

    private void isadmin(){

        userNamewtf = new LoginController();
        System.out.println(userNamewtf.isAdmin());
    }

    @FXML
    public void initialize(){
        isadmin();
    }

    @FXML
    public void showWhiteInsert() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("whiteInsert.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setTitle("WineAmp");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }

    @FXML
    public void showRedInsert() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("redInsert.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setTitle("WineAmp");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    @FXML
    public void showRoseInsert() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("roseInsert.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setTitle("WineAmp");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    @FXML
    public void showSparklingInsert() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sparklingInsert.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setTitle("WineAmp");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    @FXML
    public void showDessertInsert() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dessertInsert.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setTitle("WineAmp");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    @FXML
    public void showLocationInsert() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("locationInsert.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setTitle("WineAmp");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    @FXML
    public void showLocationSearch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("locationSearch.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setTitle("WineAmp");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    @FXML
    public void showLocationDelete() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("locationDelete.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setTitle("WineAmp");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }


}
