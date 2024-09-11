package service;

import java.io.File;
import java.io.IOException;

public class FileService {

    private final static String path = "dataBase.txt";

    public void createFile() {
        File fIle = new File(path);
        try {
            if (fIle.createNewFile()) {
                System.out.println("File create");
            } else System.out.println("File already exists");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File getFile() {
        File file = new File(path);
        return file;
    }
}
