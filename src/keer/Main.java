package keer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import keer.domain.FileLoader;
import keer.domain.StaffFileLoader;

import java.awt.event.MouseEvent;
import java.beans.EventHandler;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

//        FileLoader staffFileLoader = new StaffFileLoader();
//        staffFileLoader.setFilePath("travel.xlsx");
//        staffFileLoader.loadData();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = fxmlLoader.load();
        ((Controller)fxmlLoader.getController()).initConfigs(primaryStage, root);

        primaryStage.setTitle("出差選擇器");

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
