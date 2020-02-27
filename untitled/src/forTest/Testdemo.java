package forTest;

import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;


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

        TreeView<String> treeView = new TreeView<>();

        TreeItem<String> treeItem = new TreeItem<>("我");

        TreeItem<String> treeItem1 = new TreeItem<>("你");
        TreeItem<String> treeItem2 = new TreeItem<>("它");
        TreeItem<String> treeItem3 = new TreeItem<>("他");
        TreeItem<String> treeItem4 = new TreeItem<>("他");

        treeItem3.getChildren().add(treeItem4);

        treeItem.getChildren().addAll(treeItem1,treeItem2,treeItem3);

        treeItem.setExpanded(true);

        treeView.setRoot(treeItem);



        treeItem.addEventHandler(TreeItem.valueChangedEvent(), new EventHandler<TreeItem.TreeModificationEvent<String>>() {
            @Override
            public void handle(TreeItem.TreeModificationEvent<String> event) {
                System.out.println("changed");
            }
        });

        treeItem.setValue("123312");

        TreeCell<String> treeCell = new TreeCell<>();



        treeView.getSelectionModel().getSelectedItems().addListener((ListChangeListener<TreeItem<String>>) c -> {
            System.out.println(treeView.getRow(c.getList().get(0)));

        });



        AnchorPane anchorPane = new AnchorPane();

        anchorPane.getChildren().add(treeView);

        AnchorPane.setTopAnchor(treeView,100.0);

        Scene scene = new Scene(anchorPane);

        primaryStage.setScene(scene);
        primaryStage.show();





    }


class task extends ScheduledService<Void> {

    private  AnchorPane anchorPane;

    task(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                File file = new File("src/jpg");

                BackgroundSize backgroundSize = new BackgroundSize(1424,739,true,true
                        ,false,true);
                File[] files = file.listFiles();

                assert files != null;
                int index = new Random().nextInt(files.length);

                FileInputStream fileInputStream = new FileInputStream(files[index]);
                BackgroundImage backgroundImage1 = new BackgroundImage(new Image(fileInputStream),null,null,null,backgroundSize);

                Background background = new Background(backgroundImage1);
                anchorPane.setBackground(background);

                return null;
            }
        };
    }
}
}

