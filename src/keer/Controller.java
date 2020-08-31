package keer;

import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javafx.beans.value.ChangeListener;

import javafx.scene.input.MouseEvent;
import keer.domain.AccompanyStaff;
import keer.domain.loader.AccompanyFileLoader;
import keer.domain.Staff;
import keer.domain.loader.StaffFileLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    public TabPane mainTabPane;
    public Button confirmBtn;
    public TextField totalTimeInput;
    public TextField personPerTimeInput;
    public Tab togetherRatioTab;
    public AnchorPane ratioAnchor;
    public Button addAccompanyBtn;
    public TableView<ObservableList<String>> accompanyTable;

    private List<ComboBox<String>> accompanyComboBoxes;
    public ComboBox<String> accompanyA;
    public ComboBox<String> accompanyB;

    private Stage stage;
    private Parent root;

    public Controller(){
        accompanyComboBoxes = new ArrayList<>();
    }


    public void initConfigs(Stage stage, Parent root) throws IOException {
        this.stage = stage;
        this.root = root;

        StaffFileLoader staffFileLoader = new StaffFileLoader();
        List<Staff> staffs = loadStaffs(staffFileLoader);

        AccompanyFileLoader accompanyFileLoader = new AccompanyFileLoader();
        List<AccompanyStaff> accompanyStaffs = loadAccompanyStaffs(accompanyFileLoader);
        List<String> accompanyTitle = accompanyFileLoader.getTitle();

        accompanyComboBoxes.add(accompanyA);
        accompanyComboBoxes.add(accompanyB);
        fillAccompanyComboBoxes(staffFileLoader.getStaffNames(staffs));

        makeTableViewColumn(accompanyTitle);
        makeTableViewItems(accompanyFileLoader.getAccompanyStaffNames());

        setPresentation();
        setSizeChangedPresentation();
    }


    private void makeTableViewColumn(List<String> accompanyTitle) {
        for (String title : accompanyTitle) {
            TableColumn<ObservableList<String>, String> column = new TableColumn<>(title);
            accompanyTable.getColumns().add(column);
        }
    }

    private void makeTableViewItems(List<List<String>> accompanyStaffNames) {
        for (List<String> stringList : accompanyStaffNames){
            accompanyTable.getItems().add((ObservableList<String>) stringList);
        }
    }

    private void fillAccompanyComboBoxes(List<String> items) {
        for (ComboBox<String> comboBoxItem: accompanyComboBoxes){
            comboBoxItem.getItems().addAll(items);
        }
    }

    private void setSizeChangedPresentation() {
        ChangeListener<Number> listener = (observable, oldValue, newValue) -> makeComponentFitSceneSize();
        stage.widthProperty().addListener(listener);
        stage.heightProperty().addListener(listener);
    }

    private void setPresentation() {
        Rectangle2D userScreenBounds = Screen.getPrimary().getBounds();
        System.out.println(userScreenBounds);
        stage.setScene(new Scene(root, userScreenBounds.getMaxX() / 2.0, userScreenBounds.getMaxY() / 2.0));
        stage.centerOnScreen();
    }

    public void makeComponentFitSceneSize(){
        double height = this.stage.getScene().getHeight() ;
        double width = this.stage.getScene().getWidth() ;

        accompanyTable.setPrefWidth(ratioAnchor.getWidth() - 100);
        mainTabPane.setPrefSize(width-10, height-10);
    }

    public void determineTravel(MouseEvent mouseEvent) {
        System.out.println("今年共出差次數 :" + totalTimeInput.getText());
    }

    private List<Staff> loadStaffs(StaffFileLoader staffFileLoader) throws IOException {
        staffFileLoader.setFilePath("travel.xlsx");
        staffFileLoader.loadData();
        return staffFileLoader.getStaffs();
    }
    private List<AccompanyStaff> loadAccompanyStaffs(AccompanyFileLoader accompanyFileLoader) throws IOException {
        accompanyFileLoader.setFilePath("accompany.xlsx");
        accompanyFileLoader.loadData();
        return accompanyFileLoader.getAccompanyStaffs();
    }

    public void addAccompany(MouseEvent mouseEvent) {
        List<String> accompanyStaffs = new ArrayList<>();

    }



}
