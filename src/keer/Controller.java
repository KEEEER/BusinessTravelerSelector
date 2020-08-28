package keer;

import javafx.fxml.FXML;
import javafx.scene.AmbientLight;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller {


    public Label label1;
    public AmbientLight light1;
    public TextField yearTotalTravel;
    public TextField travelerPerTime;
    public TextField filePath;
    public Button confirm;

    @FXML
    public void confirmListener(MouseEvent mouseEvent) {
        if(mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
            System.out.println(MouseEvent.MOUSE_CLICKED);
        }
    }
}
