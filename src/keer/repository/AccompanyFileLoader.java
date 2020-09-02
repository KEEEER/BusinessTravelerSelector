package keer.repository;

import keer.domain.AccompanyStaffSet;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AccompanyFileLoader implements FileLoader {
    private List<AccompanyStaffSet> accompanyStaffSets;
    private List<String> title;
    private String filePath;
    private FileOutputStream fileOutputStream;


    private XSSFWorkbook workbook;


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
        this.workbook = new XSSFWorkbook(fileInputStream);
        return workbook.getSheetAt(0);
    }

    public void removeRow(int rowIndex) throws IOException {
        fileOutputStream = new FileOutputStream(filePath);
        XSSFSheet sheet = this.workbook.getSheetAt(0);
        int lastRowNum=sheet.getLastRowNum();
        if(rowIndex>=0&&rowIndex<lastRowNum){
            sheet.shiftRows(rowIndex+1,lastRowNum, -1);
        }
        if(rowIndex==lastRowNum){
            XSSFRow removingRow=sheet.getRow(rowIndex);
            if(removingRow!=null){
                sheet.removeRow(removingRow);
            }
        }
        workbook.write(fileOutputStream);
    }

    public void addRow(AccompanyStaffSet accompanyStaffSet) throws IOException {
        fileOutputStream = new FileOutputStream(filePath);
        XSSFSheet sheet = this.workbook.getSheetAt(0);
        Row row = sheet.createRow(sheet.getLastRowNum() + 1);
        for(int i=0 ; i<accompanyStaffSet.getAccompanyStaffs().size() ; i++){
            row.createCell(i).setCellValue(accompanyStaffSet.getAccompanyStaffs().get(i));
        }
        workbook.write(fileOutputStream);
    }

    public List<AccompanyStaffSet> getAccompanyStaffSets() {
        return this.accompanyStaffSets;
    }
    public List<String> getTitle(){
        return this.title;
    }

}
