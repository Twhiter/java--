package tool;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * @author T.Whiter
 * @Date 2020/2/20 17:26
 * @Version 1.0
 */
public class Tool {
    public static KeyFrame setNodeAppear(Duration duration, Node node) {
        node.setOpacity(0);
        node.setScaleX(0);
        node.setLayoutY(0);

        KeyValue opacity = new KeyValue(node.opacityProperty(),1);
        KeyValue smallerX = new KeyValue(node.scaleXProperty(),1);
        KeyValue smallerY = new KeyValue(node.scaleYProperty(),1);

        return new KeyFrame(duration,opacity,smallerX,smallerY);
    }

    public static KeyFrame setNodeDisappear(Duration duration,Node node) {
        KeyValue opacity = new KeyValue(node.opacityProperty(),0);
        KeyValue smallerX = new KeyValue(node.scaleXProperty(),0);
        KeyValue smallerY = new KeyValue(node.scaleYProperty(),0);

        return new KeyFrame(duration,opacity,smallerX,smallerY);
    }

    public static TextField initializeNode(int fontSize,String content,double maxWidth,double maxHeight
    ,double minWidth,double minHeight) {
        TextField textField = new TextField(content);

        textField.setEditable(false);
        textField.setMinSize(minWidth, minHeight);
        textField.setMaxSize(maxWidth, maxHeight);
        textField.setFocusTraversable(false);
        textField.setFont(Font.font(fontSize));
        textField.setAlignment(Pos.CENTER);

        return textField;
    }

    /***
     * 返回替换组件动画的TimeLine类
     * @param parent 容纳disappear的容器
     * @param disappear 要被替换的组件
     * @param appear 替换成的组件
     * @param appearTime 新的组件出现时间
     * @param fadeTime 被替换的组件消失的时间
     * @return 该动画的TimeLine类
     */
    public static Timeline replaceNode(ObservableList<Node> parent,Node disappear,Node appear,
                                       Duration fadeTime,Duration appearTime) {

        Timeline timeline = new Timeline(setNodeDisappear(fadeTime,disappear));

        timeline.setOnFinished(event -> {
            parent.remove(disappear);
            parent.add(appear);

            AnchorPane.setTopAnchor(appear,100.0);
            AnchorPane.setLeftAnchor(appear,100.0);
            Timeline timeline1 = new Timeline(setNodeAppear(appearTime,appear));
            timeline1.play();
        });

        return timeline;
    }
}
