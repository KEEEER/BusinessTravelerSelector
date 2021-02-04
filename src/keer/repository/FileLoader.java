package keer.repository;

import keer.domain.AccompanyStaffSet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FileLoader {
    public void setFilePath(String filePath);
    public void loadData() throws IOException;
}
