package controllers;

import database.DataBase;
import entities.*;
import exceptions.DataBaseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.fxUtilities;
import java.util.List;
import java.util.OptionalLong;

public class WhiteInsertController {

    private static final Logger logger = LoggerFactory.getLogger(HelloApplication.class);
    private List<Winery> listOfWineries;
    private List<FoodType> listOfFoodTypes;
    @FXML
    private TextField alcoholTextField;
    @FXML
    private TextField gemistCompatibilityTextField;
    @FXML
    private TextField grapeNameTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private TextField ratingTextField;
    @FXML
    private TextField volumeTextField;
    @FXML
    private ComboBox<Winery> wineryComboBox;
    @FXML
    private ComboBox<FoodType> suitableFoodComboBox;
    @FXML
    private TextField yearOfProductionTextField;

    @FXML
    public void initialize(){

        try{
            listOfWineries = DataBase.allWineries();
            listOfFoodTypes = DataBase.allFoodTypes();

        }catch (DataBaseException dbex){
            logger.error(DataBase.DB_CONNECTION_EXCEPTION);
            fxUtilities.errorMessage(DataBase.DB_CONNECTION_EXCEPTION);
        }

        ObservableList<Winery> obsListWineries = FXCollections.observableArrayList(listOfWineries);
        ObservableList<FoodType> obsListFoodTypes = FXCollections.observableArrayList(listOfFoodTypes);

        wineryComboBox.setItems(obsListWineries);
        suitableFoodComboBox.setItems(obsListFoodTypes);
    }
    @FXML
    public void inserWhiteToDB(){

        StringBuilder errorMessages = new StringBuilder();


        if(grapeNameTextField.getText().isEmpty()){
            errorMessages.append(" Grape Name,");
        }
        if(alcoholTextField.getText().isEmpty()){
            errorMessages.append(" Alcohol %,");
        }
        if(gemistCompatibilityTextField.getText().isEmpty()){
            errorMessages.append(" Gemist Compatibility,");
        }
        if(priceTextField.getText().isEmpty()){
            errorMessages.append(" Price,");
        }
        if(ratingTextField.getText().isEmpty()){
            errorMessages.append(" Rating,");
        }
        if(yearOfProductionTextField.getText().isEmpty()){
            errorMessages.append(" Year of production,");
        }
        if(volumeTextField.getText().isEmpty()){
            errorMessages.append(" Volume,");
        }
        if(wineryComboBox.getSelectionModel().isEmpty()) {
            errorMessages.append(" Winery,");
        }
        if(suitableFoodComboBox.getSelectionModel().isEmpty()){
            errorMessages.append(" Food type,");
        }

        if (errorMessages.isEmpty()){

            Double enteredAlcohol = Double.parseDouble(alcoholTextField.getText());
            Integer enteredGemistCopatibiltiy = Integer.parseInt(gemistCompatibilityTextField.getText());
            String enteredGrapeName = grapeNameTextField.getText();
            Integer enteredYearOfProduction = Integer.parseInt(yearOfProductionTextField.getText());
            Double enteredPrice = Double.parseDouble(priceTextField.getText());
            Double enteredRating = Double.parseDouble(ratingTextField.getText());
            Double enteredVolume = Double.parseDouble(volumeTextField.getText());
            int indexFoodType=suitableFoodComboBox.getSelectionModel().getSelectedIndex();
            int indexWinery=wineryComboBox.getSelectionModel().getSelectedIndex();

            OptionalLong maximumID = listOfWineries.stream()
                    .mapToLong(Entity::getId).max();

            System.out.println(enteredGemistCopatibiltiy + " " + gemistCompatibilityTextField.getText());
            WhiteWine whiteWine = new WhiteWine(maximumID.getAsLong()+1,enteredGrapeName,enteredYearOfProduction,enteredAlcohol,
                    listOfFoodTypes.get(indexFoodType),listOfWineries.get(indexWinery),
                    new SellingDetails(enteredRating,enteredVolume,enteredPrice),enteredGemistCopatibiltiy);
            System.out.println(whiteWine);
            try{
                DataBase.addWine(whiteWine);
                logger.info("Successfully added new wine to database!");
                fxUtilities.infoMessage("Successfully added new wine to database!");
            }catch (DataBaseException ex){
                logger.error("Failed adding new wine.");
                fxUtilities.errorMessage("Failed adding new wine.");
            }
        }
        else{
            fxUtilities.errorMessage("Missing: " + errorMessages);
        }
    }

}
