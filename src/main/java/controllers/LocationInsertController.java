package controllers;

import database.DataBase;
import entities.Location;
import entities.Region;
import entities.Winery;
import exceptions.DataBaseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.fxUtilities;

import java.util.List;

public class LocationInsertController {
    private static final Logger logger = LoggerFactory.getLogger(HelloApplication.class);
    private List<Location> listOfExhistingLocation;
    @FXML
    private Button insertWine;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField postCodeTextField;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private ComboBox<Region> regionComboBox;

    @FXML
    void insertLocationToDB(ActionEvent event) {

        StringBuilder errorMessages = new StringBuilder();

        if (postCodeTextField.getText().isEmpty()){
            errorMessages.append(" postal code,");
        }
        if (nameTextField.getText().isEmpty()){
            errorMessages.append(" name,");
        }
        if (regionComboBox.getSelectionModel().isEmpty()){
            errorMessages.append(" region,");
        }

        if (errorMessages.isEmpty()){
            long enteredPostalCode = Long.parseLong(postCodeTextField.getText());
            String enteredName = nameTextField.getText();
            Region region = regionComboBox.getSelectionModel().getSelectedItem();


            Location newLocation = new Location.Builder()
                    .withPostalCode(enteredPostalCode)
                    .withTown(enteredName)
                    .withRegion(region).build();

            System.out.println(newLocation);
            try{
                DataBase.addLocation(newLocation);
                logger.info("Successfully added new location to database!");
                fxUtilities.infoMessage("Successfully added new location to database!");
            }catch (DataBaseException ex){
                logger.error("Failed adding new location.");
                fxUtilities.errorMessage("Failed adding new location.");
            }
        }
        else{
            fxUtilities.errorMessage("Missing: " + errorMessages);
        }

    }
    @FXML
    public void initialize(){

        try{
            listOfExhistingLocation = DataBase.allLocation();
        }catch (DataBaseException dbex){
            logger.error(DataBase.DB_CONNECTION_EXCEPTION);
            fxUtilities.errorMessage(DataBase.DB_CONNECTION_EXCEPTION);
        }
        List<Region> regions = Region.allRegions();
        ObservableList<Region> obsListRegion = FXCollections.observableArrayList(regions);
        regionComboBox.setItems(obsListRegion);
    }

}
