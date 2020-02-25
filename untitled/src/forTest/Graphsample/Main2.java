package forTest.Graphsample;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * @author T.Whiter
 * @Date 2020/2/24 9:39
 * @Version 1.0
 */
public class Main2 extends Application {


    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane anchorPane = new AnchorPane();

        Person2 person2 = new Person2("1",112);
        Person2 person21 = new Person2("2,",12);
        Person2 person22 = new Person2("121",45);


        ObservableList<Person2> list = FXCollections.observableArrayList();


        list.addAll(person2,person21,person22);


        TableView<Person2> tableView = new TableView<>();

        tableView.setItems(list);
        tableView.setEditable(true);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        TableColumn<Person2,String> firstColumn = new TableColumn<>("firstName");
        firstColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFirstName()));




        TableColumn<Person2,Number> secondColumn = new TableColumn<>("ID");

        secondColumn.setCellValueFactory(param -> new SimpleDoubleProperty(param.getValue().getID()));

        Button button = new Button("1231");

        firstColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        secondColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<>() {
            @Override
            public String toString(Number object) {
                return object.toString();
            }

            @Override
            public Number fromString(String string) {
                try {
                    return Integer.valueOf(string);
                }
                catch (NumberFormatException e) {
                    int row = tableView.getFocusModel().getFocusedCell().getRow();
                    return list.get(row).getID();
                }
            }
        }));

        button.setOnMouseClicked(event -> {
            System.out.println(person2.getFirstName());
            System.out.println(person2.getID());
        });

        tableView.getColumns().setAll(firstColumn,secondColumn);



        AnchorPane.setTopAnchor(tableView,100.0);
        AnchorPane.setLeftAnchor(tableView,100.0);


        anchorPane.getChildren().addAll(tableView,button);

        Scene scene = new Scene(anchorPane);

        primaryStage.setScene(scene);
        primaryStage.show();



    }
}
