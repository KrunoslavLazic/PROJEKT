package controllers;

import database.DataBase;
import entities.Location;
import entities.Region;
import exceptions.DataBaseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.fxUtilities;

import java.util.List;

public class LocationDeleteController {

    private static final Logger logger = LoggerFactory.getLogger(HelloApplication.class);
    private List<Location> everyLocation;
    @FXML
    private CheckBox confirmationCheckBox;
    @FXML
    private ComboBox<Location> locationComboBox;

    @FXML
    void delete(ActionEvent event) {

        if (confirmationCheckBox.isSelected()){
            Location location = locationComboBox.getSelectionModel().getSelectedItem();
            try{
                DataBase.deleteLocation(location);
                logger.info("Successfully deleted location.");
                fxUtilities.infoMessage("Successfully deleted location " + location);
                fillChoiceBox();
            }catch (DataBaseException ex){
                logger.error("Failed deleting location.");
                fxUtilities.errorMessage("Failed deleting location.");
            }
        }
        else {
            fxUtilities.errorMessage("Please confirm.");
        }
    }
    @FXML
    public void initialize(){
        fillChoiceBox();
    }

    private void fillChoiceBox(){
        try{
            everyLocation = DataBase.allLocation();
        }catch (DataBaseException dbex){
            logger.error(DataBase.DB_CONNECTION_EXCEPTION);
            fxUtilities.errorMessage(DataBase.DB_CONNECTION_EXCEPTION);
        }
        ObservableList<Location> obsListLocation = FXCollections.observableArrayList(everyLocation);
        locationComboBox.setItems(obsListLocation);
    }


}
