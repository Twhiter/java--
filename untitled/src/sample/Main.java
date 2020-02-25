package sample;
/**
 * @author T.Whiter
 * @Date 2020/2/21 11:20
 * @Version 1.0
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../fxml/mainSample.fxml"));



        Scene scene = new Scene(parent);


        primaryStage.setScene(scene);

        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
