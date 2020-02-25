package forTest.Graphsample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class GraphMain extends Application {


    public static void main(String[] args) {
        launch(args);
    }




    @Override
    public void start(Stage primaryStage) throws Exception {


        AnchorPane anchorPane = new AnchorPane();

        Person person1 = new Person("蔡","连发");
        Person person2 = new Person("熊","佳金");
        Person person33 = new Person("葛","宇聪");


        ObservableList<Person> list = FXCollections.observableArrayList(person1,person2,person33);


        TableView<Person> tableView = new TableView<>(list);

        TableColumn<Person,String> firstColumn = new TableColumn<>("姓");
        firstColumn.setCellValueFactory(param -> param.getValue().firstNameProperty());

        TableColumn<Person,String> secondColumn = new TableColumn<>("名");
        secondColumn.setCellValueFactory(param -> param.getValue().lastNameProperty());

        tableView.getColumns().setAll(firstColumn,secondColumn);

        tableView.setEditable(true);

        firstColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        secondColumn.setCellFactory(TextFieldTableCell.forTableColumn());


        Button button = new Button("delete");
        Button button1 = new Button("add");
        Button button2 = new Button("watch");


        button.setOnMouseClicked(event -> {
            ObservableList<Person> deleteList = tableView.getSelectionModel().getSelectedItems();

            list.removeAll(deleteList);

        });


        button1.setOnMouseClicked(event -> {
            list.add(new Person());
            tableView.getSelectionModel().select(list.size() - 1,firstColumn);
            tableView.requestFocus();
        });


        button2.setOnMouseClicked(event -> {
            System.out.println(person1.firstNameProperty());
            System.out.println(person1.lastNameProperty());
        });


        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.setTableMenuButtonVisible(true);



        anchorPane.getChildren().add(button1);
        anchorPane.getChildren().addAll(button,button2);

        AnchorPane.setLeftAnchor(button,400.0);
        AnchorPane.setTopAnchor(button,200.0);

        AnchorPane.setLeftAnchor(button1,300.0);
        AnchorPane.setTopAnchor(button1,400.0);

        AnchorPane.setLeftAnchor(button2,400.0);
        AnchorPane.setTopAnchor(button2,500.0);







        anchorPane.getChildren().add(tableView);

        Scene scene = new Scene(anchorPane);

        primaryStage.setScene(scene);
        primaryStage.show();






    }
}
