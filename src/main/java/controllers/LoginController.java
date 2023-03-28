package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.fxUtilities;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class LoginController {

    private static final String LOGIN_DATA_FILE_NAME = "C:\\Users\\kruno\\Documents\\Faks\\Sem V\\Programiranje u jeziku Java\\Projekti\\PROJEKT\\src\\main\\resources\\login\\data.csv";
    private static final Logger logger = LoggerFactory.getLogger(HelloApplication.class);
    private static boolean isAdmin= false;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private PasswordField hiddenPasswordField;
    @FXML
    private CheckBox showPassword;

    HashMap<String,String> loginInfo = new HashMap<>();

    @FXML
    void changeVisibility(ActionEvent event) {
        if (showPassword.isSelected()){
            passwordTextField.setText(hiddenPasswordField.getText());
            passwordTextField.setVisible(true);
            hiddenPasswordField.setVisible(false);
        }
        else{
            hiddenPasswordField.setText(passwordTextField.getText());
            hiddenPasswordField.setVisible(true);
            passwordTextField.setVisible(false);
        }
    }

    @FXML
    void loginUser() throws IOException, NoSuchAlgorithmException {

        if (validInput()){
            String username = usernameTextField.getText();
            String passworord = getPassword();
            updateLoginUsernameAndPassword();

            String encriptedPassword = loginInfo.get(username);
            if (encryptString(passworord).equals(encriptedPassword)){
                System.out.println("Login successful!");
                if (Objects.equals(username, "admin")){
                    isAdmin=true;
                    System.out.println("You are an administrator!" + isAdmin);
                }
                else{
                    isAdmin=false;
                    System.out.println("Ako nisi admin jesi zapravo itko?");
                    System.out.println(isAdmin);
                }
                showHome();
            }
            else{
                fxUtilities.errorMessage("Failed login!");
            }
        }
    }

    private void updateLoginUsernameAndPassword() throws IOException{

        loginInfo.clear();
        loginInfo = new HashMap<>();
        try(Scanner scanner = new Scanner(new File(LOGIN_DATA_FILE_NAME))){
            while (scanner.hasNext()) {
                String[] usernameAndPassword = scanner.nextLine().split(",");
                loginInfo.put(usernameAndPassword[0],usernameAndPassword[1]);
            }
        }catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
            logger.error(fnfe.getMessage());
            fxUtilities.errorMessage(fnfe.getMessage());
        }

    }
    private void writeToFile() throws IOException, NoSuchAlgorithmException{

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(LOGIN_DATA_FILE_NAME,true))){

            writer.write(usernameTextField.getText() + "," + encryptString(getPassword()) +"\n");
            fxUtilities.infoMessage("Your account has been created.");
            logger.info("New account " + usernameTextField.getText() + " created.");

        }catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
            logger.error(fnfe.getMessage());
            fxUtilities.errorMessage(fnfe.getMessage());
        }
    }
    @FXML
    void registerUser(ActionEvent event) throws IOException, NoSuchAlgorithmException {

        if(validInput()){
            if (loginInfo.containsKey(usernameTextField.getText())) {
                fxUtilities.errorMessage("User already exists!");
            } else {
                writeToFile();
            }
        }

    }
    private String encryptString(String toEncrypt) throws NoSuchAlgorithmException{

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] byteMd = md.digest(toEncrypt.getBytes());
        BigInteger encripted = new BigInteger(1,byteMd);
        return encripted.toString(16);

    }

    private boolean validInput(){

        if (usernameTextField.getText().isEmpty() || getPassword().isEmpty()){
            fxUtilities.errorMessage("Insert everything!");
            return false;
        }
        else return true;
    }
    private String getPassword(){
        if (passwordTextField.isVisible()){
            return passwordTextField.getText();
        }
        else{
            return hiddenPasswordField.getText();
        }
    }

    @FXML
    public void showHome() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menuBar.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloApplication.getMainStage().setTitle("WineAmp");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public boolean isAdmin(){
        return isAdmin;
    }

}

