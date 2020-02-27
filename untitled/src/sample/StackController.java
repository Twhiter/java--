package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;
import tool.Tool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * @author T.Whiter
 * @Date 2020/2/20 11:12
 * @Version 1.0
 */
public class StackController implements Controller {
    @FXML
    private Button push,pop;

    @FXML
    private TextField input;

    @FXML
    private VBox vBox;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ScrollPane scrollPane;

    private Tooltip tooltip;

    private static final double MAX_WIDTH = 200;
    private static final double MAX_HEIGHT = 85;
    private static final double MIN_WIDTH = 200;
    private static final double MIN_HEIGHT = 85;
    private static final int FONT_SIZE = 41;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tooltip = new Tooltip("不能将空入队列");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        input.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty())
                tooltip.hide();
        });

    }

    @FXML
    public void push() {
        String content = input.getText();

        if (content.isEmpty()) {
            tooltip.show(input,Tooltip.getWindows().get(0).getX() + 252 - 100,Tooltip.getWindows().get(0).getY() + 286 - 100);
            return;
        }

        input.clear();

        TextField textField = Tool.initializeNode(FONT_SIZE,content,MAX_WIDTH,MAX_HEIGHT,MIN_WIDTH,MIN_HEIGHT);

        KeyFrame keyFrame = Tool.setNodeAppear(Duration.seconds(0.75),textField);

        Timeline timeline = new Timeline(keyFrame);

        vBox.getChildren().add(0,textField);

        timeline.play();

    }

    @FXML
    public void pop() {
        try {

            KeyFrame keyFrame = Tool.setNodeDisappear(Duration.seconds(0.75), vBox.getChildren().get(0));

            Timeline timeline = new Timeline(keyFrame);
            timeline.setOnFinished(event -> vBox.getChildren().remove(0));

            timeline.play();
        }
        catch (NullPointerException | IndexOutOfBoundsException ignored){}
    }

    @Override
    public void read(@NotNull File file) {
        try {
            Scanner in = new Scanner(file,StandardCharsets.UTF_8);
            vBox.getChildren().clear();
            in.next();

            //不断的将内容提取出来
            while (in.hasNext())
                vBox.getChildren().add(Tool.initializeNode(FONT_SIZE,
                        in.next(),MAX_WIDTH,MAX_HEIGHT,MIN_WIDTH,MIN_HEIGHT));
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(@NotNull File file) {
        try {
            if (!file.exists())
                if (!file.createNewFile())
                    return;

            FileWriter fileWriter = new FileWriter(file, StandardCharsets.UTF_8,false);
            fileWriter.write("stack\n");

            for (var child : vBox.getChildren())
                fileWriter.write(((TextField) child).getText() + '\n');
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
