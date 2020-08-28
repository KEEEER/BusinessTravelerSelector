package keer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import keer.domain.FileLoader;
import keer.domain.StaffFileLoader;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FileLoader staffFileLoader = new StaffFileLoader();
        staffFileLoader.setFilePath("travel.xlsx");
        staffFileLoader.loadData();

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("出差選擇器");
        primaryStage.setScene(new Scene(root, 500, 300));

        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
