package sample;

import graph.GenerifiedEdge;
import graph.GenerifiedGraph;
import graph.Graphable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import org.jetbrains.annotations.NotNull;
import refactor.UIEdge;

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
 * @Date 2020/2/21 8:08
 * @Version 1.0
 */
public class GraphController implements Controller {

    @FXML
    private Button add,delete;

    @FXML
    private TableView inputTable,outputTable;

    @FXML
    private TableColumn<UIEdge,String> from,to;

    @FXML
    private TableColumn<UIEdge,String> spFrom,spTo;

    @FXML
    private TableColumn<UIEdge,Number> sp;

    @FXML
    private TableColumn<UIEdge,Number> weight;


    private ObservableList<UIEdge> list;

    private ObservableList<UIEdge> spList;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = FXCollections.observableArrayList();
        spList = FXCollections.observableArrayList();



        outputTable.setItems(spList);
        outputTable.setTableMenuButtonVisible(true);
        spFrom.setCellValueFactory(param -> param.getValue().getFrom());
        spTo.setCellValueFactory(param -> param.getValue().getTo());
        sp.setCellValueFactory(param -> param.getValue().weightProperty());
        outputTable.getColumns().setAll(spFrom,spTo,sp);



        inputTable.setTableMenuButtonVisible(true);
        inputTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        inputTable.setItems(list);


        from.setCellValueFactory(param -> param.getValue().getFrom());
        to.setCellValueFactory(param -> param.getValue().getTo());
        weight.setCellValueFactory(param -> param.getValue().weightProperty());
        inputTable.getColumns().setAll(from,to,weight);

        inputTable.setEditable(true);
        from.setCellFactory(TextFieldTableCell.forTableColumn());
        to.setCellFactory(TextFieldTableCell.forTableColumn());
        weight.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<>() {
            @Override
            public String toString(Number object) {
                return object.toString();
            }

            @Override
            public Number fromString(String string) {
                try {
                    return Double.valueOf(string);
                }
                //如果输入的为格式错误,则不修改原有的值
                catch (NumberFormatException e) {
                    int row = inputTable.getFocusModel().getFocusedCell().getRow();
                    return list.get(row).weightProperty().doubleValue();
                }
            }
        }));

    }

    @FXML
    private void add() {
        list.add(new UIEdge());
    }

    @FXML
    private void delete() {
        ObservableList<UIEdge> deleteList = inputTable.getSelectionModel().getSelectedItems();
        list.removeAll(deleteList);
    }


    @Override
    public void read(@NotNull File file) {
        try {
            Scanner in = new Scanner(file, StandardCharsets.UTF_8);
            in.nextLine();

            while (in.hasNext()) {
                String from = in.nextLine();
                String to = in.nextLine();
                double weight = in.nextDouble();
                in.nextLine();
                in.nextLine();
                list.add(new UIEdge(from,to,weight));
            }

            in.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(File file) {
        try {
            PrintStream printStream = new PrintStream(file,StandardCharsets.UTF_8);
            System.setOut(printStream);

            System.out.println("graph");

            for (var uiEdge : list) {
                if (uiEdge.getFrom().getValue().isEmpty() || uiEdge.getTo().getValue().isEmpty())
                    continue;
                System.out.println(uiEdge.getFrom().getValue() + "\n" + uiEdge.getTo().getValue() + "\n"
                 + uiEdge.weightProperty().doubleValue() + "\n");
            }

            printStream.close();
            System.out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void done() {
        spList.clear();

        ArrayList<GenerifiedEdge<String>> edges = new ArrayList<>();
        for (var uiEdge : list)
            edges.add(uiEdge.toGenerifiedEdge());

        GenerifiedGraph<String> graph = new GenerifiedGraph<>(edges);

        var spEdgeList = graph.getShortestPath();

        for (var edge : spEdgeList) {
            if (edge.getWeight() == Graphable.INF)
                continue;
            spList.add(new UIEdge(edge.getFrom(),edge.getTo(),edge.getWeight()));
        }

        for (var e : spList) {
            System.out.println(e.getFrom());
            System.out.println(e.getTo());
            System.out.println(e.getWeight());
        }
    }
}
