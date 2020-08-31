package keer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{



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
