package com.max.registrationApp;

import javafx.scene.control.Alert;
import javafx.stage.Window;

public class HelpWindow {
    public static void showHelp(Window window){
        Alert help = new Alert(Alert.AlertType.INFORMATION);
        help.setTitle("Help Window");
        help.setHeaderText(null);
        help.setContentText("Every information you type should be valid\nPassword must contain at least 8 characters");
        help.initOwner(window);
        help.show();
    }
}
