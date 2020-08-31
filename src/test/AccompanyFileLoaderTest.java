package test;

import keer.domain.loader.AccompanyFileLoader;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class AccompanyFileLoaderTest {


    @Test
    public void getAccompanyStaffs() throws IOException {
        AccompanyFileLoader accompanyFileLoader = new AccompanyFileLoader();
        accompanyFileLoader.setFilePath("accompany.xlsx");
        accompanyFileLoader.loadData();
        assertEquals(accompanyFileLoader.getAccompanyStaffs().size(), 11);
    }
}