package forTest;

import javafx.application.Application;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
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



        WebView webView = new WebView();

        WebEngine webEngine = webView.getEngine();

        webEngine.load("http://www.baidu.com");




        AnchorPane anchorPane = new AnchorPane();

        anchorPane.getChildren().add(webView);

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

