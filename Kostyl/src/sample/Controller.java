package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import sample.Crypto.BlowFish;

import java.io.*;

public class Controller {
    public ProgressBar progressBar;
    public TextArea text;
    public String array = "";

    public void openFolder() throws IOException {
        File fileIn = new DirectoryChooser().showDialog(Main.stage);
        File fileOut = new DirectoryChooser().showDialog(Main.stage);
        BlowFish.encryptDirectory(fileIn, fileOut);
    }
   public void blowFish(ActionEvent actionEvent) {
        FXMLLoader root = null;
        try {
            root = new FXMLLoader(getClass().getResource("LogIn/logScreen.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Main.stage.setTitle("LogIn");
         Scene scene = null;
       try {
           scene = new Scene(root.load(), 320, 150);
       } catch (IOException e) {
       }
              Main.stage.setScene(scene);
        Main.stage.show();
   }
 public void exit(){
     System.exit(1);
 }

}
