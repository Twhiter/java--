package sample;

import collections.Array;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import tool.Tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * @author T.Whiter
 * @Date 2020/2/21 11:21
 * @Version 1.0
 */
public class MainController implements Initializable {


    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox<String> modeChoose;

    @FXML
    private MenuItem fileSave,fileOpen,fileSaveTo;

    private final String[] modes = {"stack","queue","bitTree","graph"};

    //对应子面板的fxml loader
    private ArrayList<FXMLLoader> subPanesLoader;

    private Array<File> files = new Array<>(modes.length);

    private Node currentPane = new Pane();


    private void init() throws IOException {
        Arrays.sort(modes);
       subPanesLoader = new ArrayList<>();
        for (String mode : modes) {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource( "/fxml/"+ mode + "Sample.fxml")
                    ,null, new JavaFXBuilderFactory());

            fxmlLoader.load();
            subPanesLoader.add(fxmlLoader);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        modeChoose.getItems().addAll(modes);
        modeChoose.valueProperty().addListener((observable, oldValue, newValue) -> {
            int index = Arrays.binarySearch(modes,newValue);

            Timeline timeline = Tool.replaceNode(
                    anchorPane.getChildren(),
                    currentPane,
                    subPanesLoader.get(index).getRoot(),
                    Duration.seconds(0.5),
                    Duration.seconds(0.5)
            );

            timeline.play();
            currentPane = subPanesLoader.get(index).getRoot();
        });

        modeChoose.setValue(modes[0]);
    }

    @FXML
    private void saveFile() {
        int index = Arrays.binarySearch(modes,modeChoose.getValue());
        File file = files.get(index);

        if (file == null)
            saveFileTo();
        else {
            Controller controller = subPanesLoader.get(index).getController();
            controller.write(file);
        }
    }

    @FXML
    private void openFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("文本文件","*.txt")
        );

        Stage stage = new Stage();
        stage.setAlwaysOnTop(true);

        File file = fileChooser.showOpenDialog(stage);

        if (file == null)
            return;


        String type = identify(file);

        int index = Arrays.binarySearch(modes,type);
        //如果已存在打开的文件,则保存之前一打开的文件
        if (files.get(index) != null)
            saveFile();

        modeChoose.setValue(type);
        Controller controller = subPanesLoader.get(index).getController();

        files.set(index,file);
        controller.read(file);
    }

    @FXML
    private void saveFileTo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("文本文件","*.txt")
        );

        Stage stage = new Stage();
        stage.setAlwaysOnTop(true);

        File file = fileChooser.showSaveDialog(stage);

        if (file == null)
            return;

        int index = Arrays.binarySearch(modes,modeChoose.getValue());
        Controller controller = subPanesLoader.get(index).getController();

        controller.write(file);
    }


    /***
     * 打开文件的第一行,读取其数据类型,并返回
     * @param file 要打开的文件
     * @return 文件的第一行的字符串.
     * @throws IOException
     */
    private String identify(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        Scanner in = new Scanner(fileInputStream);

        String type = in.next();
        in.close();
        fileInputStream.close();

        return type;
    }
}
