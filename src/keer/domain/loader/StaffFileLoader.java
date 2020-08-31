package keer.domain.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import keer.domain.Staff;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class StaffFileLoader implements FileLoader {
    private String filePath;
    private List<Staff> staffs;

    public StaffFileLoader(){
        this.staffs = new ArrayList<>();
    }

    @Override
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void loadData() throws IOException {
        this.staffs = new ArrayList<>();
        File excelFile = new File(this.filePath);
        FileInputStream fileInputStream = new FileInputStream(excelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet firstSheet = workbook.getSheetAt(0);
        for (Row row : firstSheet) {
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                this.staffs.add(new Staff(cell.toString()));
            }
        }

    }

    public List<Staff> getStaffs() {
        return this.staffs;
    }

    public List<String> getStaffNames(List<Staff> staffs){
        List<String> staffNames = new ArrayList<>();
        for(Staff staff : staffs){
            staffNames.add(staff.getStaffName());
        }
        return  staffNames;
    }
}
