package com.max.registrationApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Controller {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField emailAddress;
    @FXML
    private PasswordField passwordID;
    @FXML
    private Button registerButton;
    @FXML
    private Button helpButton;

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public void handleRegister() throws IOException {
        FileWriter fileWriter = new FileWriter("RegistrationAppData.txt", true);
        //Path path = Paths.get("RegistrationAppData.txt");
        BufferedWriter bw = new BufferedWriter(fileWriter);

        boolean atSign = checkEmail();
        boolean numberCheck = checkPhoneNumber();
        boolean passwordCheck = checkPassword();
        Window window = registerButton.getScene().getWindow();
        if(firstName.getText().isEmpty()){
            AlertWindow.showAlert(Alert.AlertType.ERROR, window, "First Name Missing", "Please enter first name");
            return;
        } else if (lastName.getText().isEmpty()){
            AlertWindow.showAlert(Alert.AlertType.ERROR, window, "Last Name Missing", "Please enter last name");
        } else if (phoneNumber.getText().isEmpty() || numberCheck){
            AlertWindow.showAlert(Alert.AlertType.ERROR, window, "Phone Number Error", "Please check your phone number");
        } else if (emailAddress.getText().isEmpty() || atSign){
            AlertWindow.showAlert(Alert.AlertType.ERROR, window, "Email Address Error", "Email address is incorrect, please try again");
        } else if (passwordID.getText().isEmpty() || passwordCheck){
            AlertWindow.showAlert(Alert.AlertType.ERROR, window, "Password Error", "Please check your password");
        } else {
            AlertWindow.showAlert(Alert.AlertType.INFORMATION, window, "Successful registration", "Your successfully registered");
            LocalDateTime now = LocalDateTime.now();
            try{
                bw.write(String.format("First Name - %s\tLast Name - %s\nPhone Number - %s\tEmail Address - %s\nPassword - %s\nDate Registered - %s\n",
                        firstName.getText(), lastName.getText(), phoneNumber.getText(), emailAddress.getText(), passwordID.getText(), dtf.format(now)));
                bw.newLine();
            } finally {
                if(bw != null){
                    bw.close();
                }
            }
        }

    }

    private boolean checkEmail(){
        String emailText = emailAddress.getText();
        boolean error = true;
        if(emailText.length() > 6) {
            for (int i = 0; i < emailText.length(); i++) {
                if (emailText.charAt(i) == '@') {
                    error = false;
                }
            }
            if (emailText.charAt(emailText.length() - 1) == '@') {
                error = true;
            }
            if (emailText.length() < 8) {
                error = true;
            }
        }
        return error;
    }
    private boolean checkPhoneNumber(){
        String phoneNumberText = phoneNumber.getText();
        boolean error = true;
        for(int i = 0; i<phoneNumberText.length(); i++){
            if(phoneNumberText.charAt(i) == '1' || phoneNumberText.charAt(i) == '2' || phoneNumberText.charAt(i) == '3'
                || phoneNumberText.charAt(i) == '4' || phoneNumberText.charAt(i) == '5' || phoneNumberText.charAt(i) == '6'
                || phoneNumberText.charAt(i) == '7' || phoneNumberText.charAt(i) == '8' || phoneNumberText.charAt(i) == '9'
                || phoneNumberText.charAt(i) == '0'){
                error = false;
            }
        }
        if(phoneNumberText.length() < 6){
            error = true;
        }
        return error;
    }
    private boolean checkPassword(){
        String passwordField = passwordID.getText();
        boolean error = true;
        if(passwordField.length() > 7){
            error = false;
        }
        return error;
    }

    public void showHelpWindow(){
        Window help = helpButton.getScene().getWindow();
        HelpWindow.showHelp(help);
    }
}
