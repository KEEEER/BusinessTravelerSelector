package keer.domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface FileLoader {
    public void setFilePath(String filePath);
    public void loadData() throws IOException;

    List<Staff> getStaffs();
}
