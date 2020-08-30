package keer;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javafx.beans.value.ChangeListener;

import javafx.scene.input.MouseEvent;

public class Controller {

    public TabPane mainTabPane;
    public Button confirmBtn;
    public TextField totalTimeInput;
    public TextField personPerTimeInput;
    public ListView ratioList;
    public Tab togetherRatioTab;
    public AnchorPane ratioAnchor;
    private Stage stage;
    private Parent root;

    public void initConfigs(Stage stage, Parent root){
        this.stage = stage;
        this.root = root;

        Rectangle2D userScreenBounds = Screen.getPrimary().getBounds();
        System.out.println(userScreenBounds);
        stage.setScene(new Scene(root, userScreenBounds.getMaxX() / 2.0, userScreenBounds.getMaxY() / 2.0));
        stage.centerOnScreen();
        //stage.initStyle(StageStyle.UNDECORATED);

        ChangeListener<Number> listener = (observable, oldValue, newValue) -> makeComponentFitSceneSize();
        stage.widthProperty().addListener(listener);
        stage.heightProperty().addListener(listener);
        togetherRatioTab.setOnSelectionChanged(event -> {
            if(togetherRatioTab.isSelected()){
                System.out.println("change to ratio!");
            }
        });

    }



    public void makeComponentFitSceneSize(){
        double height = this.stage.getScene().getHeight() ;
        double width = this.stage.getScene().getWidth() ;

        ratioList.setPrefWidth(ratioAnchor.getWidth() - 100);
        ratioList.getItems().add("hey");
        mainTabPane.setPrefSize(width-10, height-10);
    }

    public void determineTravel(MouseEvent mouseEvent) {
        System.out.println("今年共出差次數 :" + totalTimeInput.getText());
        System.out.println("每次出差人數 :" + personPerTimeInput.getText());

    }
}
