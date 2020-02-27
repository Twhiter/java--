package tool;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileInputStream;
import java.util.Random;

/**
 * @author T.Whiter
 * @Date 2020/2/26 13:45
 * @Version 1.0
 */
public class ImageChanger extends ScheduledService<Void> {
    private Pane pane;
    final private double width,height;
    private File dir = new File("src/jpg");

    public ImageChanger(Pane pane, double width, double height) {
        this.pane = pane;
        this.width = width;
        this.height = height;
    }

    public void setDir(File dir) {
        this.dir = dir;
    }

    @Override
    protected Task<Void> createTask(){
        return new Task<>() {
            @Override
            protected Void call() throws Exception {

                BackgroundSize backgroundSize = new BackgroundSize(
                        width,
                        height,
                        true,
                        true
                        ,false,
                        true);

                if (!dir.isDirectory())
                    return null;

                File[] files = dir.listFiles();

                FileInputStream fileInputStream = new FileInputStream(files[new Random().nextInt(files.length)]);

                BackgroundImage backgroundImage = new BackgroundImage(new Image(fileInputStream),
                        null,null,null,
                        backgroundSize);
                Background background = new Background(backgroundImage);
                pane.setBackground(background);

                return null;
            }
        };
    }

}
