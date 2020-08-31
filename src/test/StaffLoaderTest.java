package test;

import keer.domain.loader.FileLoader;
import keer.domain.Staff;
import keer.domain.loader.StaffFileLoader;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StaffLoaderTest {
    String filePath = "travel.xlsx";
    @Test
    public void StaffLoadStaffTest() throws IOException {
        StaffFileLoader loader = new StaffFileLoader();
        loader.setFilePath(filePath);
        loader.loadData();

        List<Staff> staffs = loader.getStaffs();
        assertEquals(staffs.size(), 11);
    }
}
