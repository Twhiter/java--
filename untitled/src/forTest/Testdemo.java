package forTest;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.Serializable;


/**
 * @author T.Whiter
 * @Date 2020/2/17 9:22
 * @Version 1.0
 */
public class Testdemo extends Application implements Serializable {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        ListView<String> listView = new ListView<>();

        ObservableList<String> list = FXCollections.observableArrayList();


        list.addAll("先序 中序 后序");

        list.addAll("1","2","3");

        listView.setItems(list);



        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(listView);


        Scene scene = new Scene(anchorPane);

        primaryStage.setScene(scene);
        primaryStage.show();





    }
}
