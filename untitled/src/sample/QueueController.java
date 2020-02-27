package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
 * @Date 2020/2/20 16:59
 * @Version 1.0
 */
public class QueueController implements Controller {


    @FXML
    private HBox hBox;

    @FXML
    private Button pop,push;

    @FXML
    private AnchorPane anchorPane;


    @FXML
    private TextField input;

    @FXML
    private ScrollPane scrollPane;

    private static final double MAX_WIDTH = 105;
    private static final double MAX_HEIGHT = 85;
    private static final double MIN_WIDTH = 105;
    private static final double MIN_HEIGHT = 85;
    private static final int FONT_SIZE = 41;

    private Tooltip tooltip;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tooltip = new Tooltip("输入不能为空");
        tooltip.setShowDelay(Duration.ZERO);
        scrollPane.setContent(hBox);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        //设置toolTip提示
        input.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty())
                tooltip.hide();
        });
    }


    @FXML
    public void push() {
        String content = input.getText();

        if (content.isEmpty()) {
            tooltip.show(input,Tooltip.getWindows().get(0).getX() + 256 - 100,Tooltip.getWindows().get(0).getY() + 574 - 100);
            return;
        }

        input.clear();
        TextField textField = Tool.initializeNode(FONT_SIZE,content, MAX_WIDTH, MAX_HEIGHT, MIN_WIDTH, MIN_HEIGHT);

        KeyFrame keyFrame = Tool.setNodeAppear(Duration.seconds(0.75),textField);
        Timeline timeline = new Timeline(keyFrame);

        timeline.play();
        hBox.getChildren().add(textField);
    }

    @FXML
    public void pop() {
        if (hBox.getChildren().size() == 0)
            return;
        Node node = hBox.getChildren().get(0);

        KeyFrame keyFrame = Tool.setNodeDisappear(Duration.seconds(0.75),node);
        Timeline timeline = new Timeline(keyFrame);
        timeline.setOnFinished(event -> hBox.getChildren().remove(0));

        timeline.play();
    }

    public void read(@NotNull File file) {
        try {
            Scanner in = new Scanner(file,StandardCharsets.UTF_8);
            hBox.getChildren().clear();
            in.next();

            //不断的将内容提取出来
            while (in.hasNext())
                hBox.getChildren().add(Tool.initializeNode(FONT_SIZE,
                        in.next(), MAX_WIDTH, MAX_HEIGHT, MIN_WIDTH, MIN_HEIGHT));
            in.close();
        }
        catch (IOException e) {
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
            fileWriter.write("queue\n");

            for (var child : hBox.getChildren())
                fileWriter.write(((TextField) child).getText() + '\n');
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
