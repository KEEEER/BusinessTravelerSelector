package keer.repository;

import keer.domain.AccompanyStaffSet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AccompanyFileLoader implements FileLoader {
    private List<AccompanyStaffSet> accompanyStaffSets;
    private List<String> title;
    private String filePath;
    public AccompanyFileLoader(){
        this.accompanyStaffSets = new ArrayList<>();
    }

    @Override
    public void setFilePath(String filePath) {
        this.filePath = filePath;
        this.title = new ArrayList<>();
    }

    @Override
    public void loadData() throws IOException {
        this.accompanyStaffSets = new ArrayList<>();
        this.title = new ArrayList<>();
        XSSFSheet firstSheet = readFile();

        boolean isTitle = true;
        for (Row row : firstSheet) {
            Iterator<Cell> cellIterator = row.cellIterator();
            List<String> rowItems = new ArrayList<>();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                rowItems.add(cell.toString());
            }
            AccompanyStaffSet accompanyStaffSet = new AccompanyStaffSet();
            for (String rowItem : rowItems) {
                if (isTitle) title.add(rowItem);
                else accompanyStaffSet.addStaff(rowItem);
            }
            if(!isTitle) this.accompanyStaffSets.add(accompanyStaffSet);
            isTitle = false;
        }
    }

    private XSSFSheet readFile() throws IOException {
        File excelFile = new File(this.filePath);
        FileInputStream fileInputStream = new FileInputStream(excelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        return workbook.getSheetAt(0);

    }

    public List<AccompanyStaffSet> getAccompanyStaffSets() {
        return this.accompanyStaffSets;
    }
    public List<String> getTitle(){
        return this.title;
    }

}
