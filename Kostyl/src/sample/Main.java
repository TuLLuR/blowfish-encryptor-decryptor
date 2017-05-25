package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class Main extends Application {
public static Stage stage;
  @Override
   public void start(Stage primaryStage) throws Exception{
       stage= primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("P E/D");
        Scene scene = new Scene(root, 200, 150);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();



    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
