package utils;

import java.io.File;

public class FileUtils {

    public static String getTestFilePath(String fileName){
        String filePath = "src/test/resources/testFiles/" + fileName;
        File file = new File(filePath);

        if(!file.exists()){
            throw new RuntimeException("File not found: " + filePath);
        }

        return file.getAbsolutePath();
    }

}
