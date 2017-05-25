package sample.LogIn;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import sample.Crypto.BlowFish;
import sample.Main;

import java.io.File;

/**
 * Created by Kiril on 23.04.2017.
 */
public class LogInController {
    public TextField login;
    public PasswordField password;
    public Label Status;
String login_t ="admin";
String password_t ="admin";
    public void logIn(ActionEvent actionEvent) {
        if (login.getText().equals(login_t) && password.getText().equals(password_t))
        {
            Status.setText("Log In Successful");
            File fileIn = new DirectoryChooser().showDialog(Main.stage);
            File fileOut = new DirectoryChooser().showDialog(Main.stage);
            BlowFish.decryptDirectory(fileIn, fileOut);
        }
        else {
            Status.setText("LogIn Failed");
        }
    }
}
