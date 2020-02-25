package forTest;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author T.Whiter
 * @Date 2020/2/22 11:09
 * @Version 1.0
 */
public class Controller implements Initializable {




    @FXML
    private Button bt;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void f() {
        bt.setText("6565656");
    }
}
