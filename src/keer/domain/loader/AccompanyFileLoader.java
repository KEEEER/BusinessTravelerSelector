package keer.domain.loader;

import keer.domain.AccompanyStaff;
import keer.domain.Staff;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AccompanyFileLoader implements FileLoader {
    private List<AccompanyStaff> accompanyStaffs;
    private List<String> title;
    private String filePath;
    public AccompanyFileLoader(){
        this.accompanyStaffs = new ArrayList<>();
    }

    @Override
    public void setFilePath(String filePath) {
        this.filePath = filePath;
        this.title = new ArrayList<>();
    }

    @Override
    public void loadData() throws IOException {
        this.accompanyStaffs = new ArrayList<>();
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
            AccompanyStaff accompanyStaff = new AccompanyStaff();
            for(int i=0 ; i<rowItems.size() - 1 ; i++){
                if (isTitle) title.add(rowItems.get(i));
                else accompanyStaff.addStaff(rowItems.get(i));
            }

            if(isTitle) title.add(rowItems.get(rowItems.size()-1));
            else accompanyStaff.setRatio(Double.parseDouble(rowItems.get(rowItems.size()-1)));

            this.accompanyStaffs.add(accompanyStaff);
            isTitle = false;
        }
    }

    private XSSFSheet readFile() throws IOException {
        File excelFile = new File(this.filePath);
        FileInputStream fileInputStream = new FileInputStream(excelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        return workbook.getSheetAt(0);

    }

    public List<AccompanyStaff> getAccompanyStaffs() {
        return this.accompanyStaffs;
    }
    public List<String> getTitle(){
        return this.title;
    }

    public List<List<String>> getAccompanyStaffNames(){
        List<List<String>> staffNames = new ArrayList<>();
        for(AccompanyStaff accompanyStaff : this.accompanyStaffs){
            staffNames.add(accompanyStaff.getAccompanyStaffs());
        }
        return  staffNames;
    }
}
