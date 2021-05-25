package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Parent root1 = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        primaryStage.setTitle("Сладости");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 700,400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
