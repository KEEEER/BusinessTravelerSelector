package keer.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javafx.beans.value.ChangeListener;

import keer.domain.AccompanyStaffSet;
import keer.repository.AccompanyFileLoader;
import keer.domain.Staff;
import keer.repository.StaffFileLoader;

import javafx.scene.input.MouseEvent;
import org.apache.poi.ss.formula.functions.Column;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    public TabPane mainTabPane;
    public Button confirmBtn;
    public TextField totalTimeInput;
    public Tab togetherRatioTab;
    public AnchorPane ratioAnchor;
    public Button addAccompanyBtn;
    public TableView<AccompanyStaffSet> accompanyTable;
    public TextField ratioInput;

    private List<ComboBox<String>> accompanyComboBoxes;
    public ComboBox<String> accompanyA;
    public ComboBox<String> accompanyB;

    private Stage stage;
    private Parent root;

    private StaffFileLoader staffFileLoader = new StaffFileLoader();
    private AccompanyFileLoader accompanyFileLoader = new AccompanyFileLoader();

    public Controller(){
        accompanyComboBoxes = new ArrayList<>();
    }


    public void initConfigs(Stage stage, Parent root) throws IOException {
        this.stage = stage;
        this.root = root;

        List<Staff> staffs = loadStaffs(staffFileLoader);
        List<AccompanyStaffSet> accompanyStaffSets = loadAccompanyStaffs(accompanyFileLoader);
        List<String> accompanyTitle = accompanyFileLoader.getTitle();

        accompanyComboBoxes.add(accompanyA);
        accompanyComboBoxes.add(accompanyB);
        fillAccompanyComboBoxes(staffFileLoader.getStaffNames(staffs));

        makeTableViewColumn(accompanyTitle);
        makeTableViewRows(accompanyStaffSets);

        setPresentation();
        setSizeChangedPresentation();
    }

    private void makeTableViewColumn(List<String> accompanyTitle) {
        for (int i = 0; i < accompanyTitle.size(); i++) {
            TableColumn<AccompanyStaffSet, String> column = new TableColumn<>(accompanyTitle.get(i));
            makeColumnRelation(column, i);
            accompanyTable.getColumns().add(column);
        }
    }

    private void makeColumnRelation(TableColumn<AccompanyStaffSet, String> column, final int finalIndex) {
        column.setCellValueFactory(p -> {
            return new ReadOnlyObjectWrapper<>(p.getValue().getAccompanyStaffs().get(finalIndex));
        });
    }

    private void makeTableViewRows(List<AccompanyStaffSet> accompanyStaffSets) {
        for(AccompanyStaffSet accompanyStaffSet : accompanyStaffSets){
            accompanyTable.getItems().add(new AccompanyStaffSet(accompanyStaffSet.getAccompanyStaffs()));
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

    private List<Staff> loadStaffs(StaffFileLoader staffFileLoader) throws IOException {
        staffFileLoader.setFilePath("travel.xlsx");
        staffFileLoader.loadData();
        return staffFileLoader.getStaffs();
    }

    private List<AccompanyStaffSet> loadAccompanyStaffs(AccompanyFileLoader accompanyFileLoader) throws IOException {
        accompanyFileLoader.setFilePath("accompany.xlsx");
        accompanyFileLoader.loadData();
        return accompanyFileLoader.getAccompanyStaffSets();
    }

    public void addAccompany(MouseEvent mouseEvent) {
        if(!isStaffMatchDuplicated() && isRatioCorrect()){
            AccompanyStaffSet accompanyStaffSet = new AccompanyStaffSet();
            accompanyTable.getItems().add()
        }

    }

    private boolean isRatioCorrect() {
        if(!ratioInput.getText().equals("")) {
            double ratioData = Double.parseDouble(ratioInput.getText());
            return ratioData <= 100.0 && ratioData > 0;
        }
        return false;

    }

    private boolean isStaffMatchDuplicated() {
        if(accompanyA.getValue().equals(accompanyB.getValue())) return false;
        for(AccompanyStaffSet accompanyStaffSet : accompanyTable.getItems()){
            for(ComboBox<String> comboBoxSelectedStaff : accompanyComboBoxes)
                for(String staffName : accompanyStaffSet.getAccompanyStaffs()){
                    if(comboBoxSelectedStaff.getValue().equals(staffName)) return true;
                }
        }
        return false;
    }

    public void determineTravel(MouseEvent mouseEvent) {
        System.out.println("今年共出差次數 :" + totalTimeInput.getText());
    }
}
