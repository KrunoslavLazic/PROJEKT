package controllers;

import database.DataBase;
import entities.Location;
import entities.Region;
import exceptions.DataBaseException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.fxUtilities;

import java.util.List;

public class LocationSearchController {

    private static final Logger logger = LoggerFactory.getLogger(HelloApplication.class);
    private List<Location> everyLocation;
    @FXML
    private TableColumn<Location, String> nameTableCol;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField postCodeTextField;

    @FXML
    private TableColumn<Location, String> postalCodeTableCol;

    @FXML
    private ComboBox<Region> regionComboBox;

    @FXML
    private TableColumn<Location, String> regionTableCol;

    @FXML
    private TableView<Location> locationTableView;

    @FXML
    public void initialize(){

        try{
            everyLocation = DataBase.allLocation();

        }catch (DataBaseException dbex){
            logger.error(DataBase.DB_CONNECTION_EXCEPTION);
            fxUtilities.errorMessage(DataBase.DB_CONNECTION_EXCEPTION);
        }
        List<Region> regions = Region.allRegions();
        ObservableList<Region> obsListRegion = FXCollections.observableArrayList(regions);
        regionComboBox.setItems(obsListRegion);

        nameTableCol.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getTown()));
        regionTableCol.setCellValueFactory(c->new SimpleStringProperty(Region.regionToString(c.getValue().getRegion())));
        postalCodeTableCol.setCellValueFactory(c->new SimpleStringProperty(Long.toString(c.getValue().getPostalCode())));

        ObservableList<Location> obsListLocation = FXCollections.observableList(everyLocation);
        locationTableView.setItems(obsListLocation);
    }
    @FXML
    void search(ActionEvent event) {

        String enteredName = nameTextField.getText();
        String enteredPostalCode = postCodeTextField.getText();
        Region region = regionComboBox.getSelectionModel().getSelectedItem();

        List<Location> filteredLocation = everyLocation.stream()
                .filter(l -> l.getTown().contains(enteredName))
                .filter(l->Long.toString(l.getPostalCode()).contains(enteredPostalCode))
                .filter(l->l.getRegion().getCountry().contains(region.getCountry()))
                .filter(l->l.getRegion().equals(region))
                .toList();
        locationTableView.setItems(FXCollections.observableList(filteredLocation));

    }

}
