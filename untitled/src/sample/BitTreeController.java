package sample;

import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.TextFieldListCell;
import org.jetbrains.annotations.NotNull;
import refactor.UIBitTree;

import java.awt.desktop.SystemSleepEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * @author T.Whiter
 * @Date 2020/2/21 12:14
 * @Version 1.0
 */
public class BitTreeController implements Controller {

    @FXML
    private ListView<String> input,output;

    @FXML
    private Button add,done,delete;

    private ObservableList<String> inputList;
    private ObservableList<String> outputList;

    private UIBitTree uiBitTree;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inputList = FXCollections.observableArrayList();
        outputList = FXCollections.observableArrayList();


        input.setItems(inputList);
        input.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        input.setEditable(true);
        input.setCellFactory(TextFieldListCell.forListView());

        output.setItems(outputList);
    }


    @Override
    public void read( @NotNull File file) {
        try {
            Scanner in = new Scanner(file, StandardCharsets.UTF_8);
            in.nextLine();

            while (in.hasNext())
                inputList.add(in.nextLine());
            in.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void write(@NotNull File file) {
        try {
            PrintStream printStream = new PrintStream(file,StandardCharsets.UTF_8);
            System.setOut(printStream);

            System.out.println("bitTree");

            for (String val : inputList)
                System.out.println(val);

            System.out.close();
            printStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void add() {
        inputList.add("#");
    }

    @FXML
    private void delete() {
        ObservableList<String> delete = input.getSelectionModel().getSelectedItems();
        inputList.removeAll(delete);
    }

    @FXML
    private void done() {
        ArrayList<String> arrayList = new ArrayList<>(inputList);
        uiBitTree = new UIBitTree(arrayList);

        outputList.clear();
        outputList.addAll(uiBitTree.dfs());
    }
}
