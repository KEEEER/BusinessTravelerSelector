package test;

import keer.domain.FileLoader;
import keer.domain.Staff;
import keer.domain.StaffFileLoader;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StaffLoaderTest {
    String filePath = "travel.xlsx";
    @Test
    public void StaffLoadStaffTest() throws IOException {
        FileLoader loader = new StaffFileLoader();
        loader.setFilePath(filePath);
        loader.loadData();

        List<Staff> staffs = loader.getStaffs();
        assertEquals(staffs.size(), 10);

    }
}
