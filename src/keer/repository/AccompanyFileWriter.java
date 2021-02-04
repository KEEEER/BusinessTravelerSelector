package keer.repository;
import keer.domain.AccompanyStaffSet;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccompanyFileWriter {
    private String filePath;
    public AccompanyFileWriter(String filePath){
        this.filePath = filePath;
    }

    public void generateFile(List<AccompanyStaffSet> accompanyStaffSets) throws IOException {
        XSSFWorkbook workbook = createWorkbook();
        workbook.createSheet("result");
        List<String> title = new ArrayList<>();
        title.add("天數");
        title.add("第一位");
        title.add("第二位");
        title.add("同行率(%)");
        addRow(workbook, title);
        for(int i=0 ; i<accompanyStaffSets.size() ; i++){
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(i+1));
            row.addAll(accompanyStaffSets.get(i).getAccompanyStaffs());
            addRow(workbook, row);
        }
        try (OutputStream fileOut = new FileOutputStream(this.filePath)) {
            workbook.write(fileOut);
        }catch (Exception ignored){}
        workbook.close();
    }
    private XSSFWorkbook createWorkbook(){
        XSSFWorkbook workbook = new XSSFWorkbook();;
        File file = new File(this.filePath);
        boolean exists =  file.exists();

        file.delete();

        try (OutputStream fileOut = new FileOutputStream(this.filePath)) {
            workbook.write(fileOut);
        }catch (Exception ignored){}

        return workbook;
    }

    public void addRow(XSSFWorkbook workbook, List<String> cellItems){
        XSSFSheet sheet = workbook.getSheetAt(0);
        Row row = sheet.createRow(sheet.getLastRowNum() + 1);
        for(int i=0 ; i<cellItems.size() ; i++) row.createCell(i).setCellValue(cellItems.get(i));
    }
}
