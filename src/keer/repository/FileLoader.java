package keer.repository;

import java.io.IOException;

public interface FileLoader {
    public void setFilePath(String filePath);
    public void loadData() throws IOException;

}
