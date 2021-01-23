package sunnypwang.commandbox.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtil {

    public static final String rootDir = "plugins/CommandBox/";

    public static void initialize() {
        new File(rootDir).mkdirs(); //Make a directory if not exist
    }

    public static File loadFIle(String filename){
        File file = new File(rootDir + filename);
        try {
            if (file.createNewFile()) { //create new file, do nothing if exists
                System.out.println("File created: " + file.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static List<String> readFile(String filename) {
        try {
            return Files.readAllLines(Paths.get(rootDir + filename));
        } catch (IOException e) {
            System.out.println("Cannot open " + rootDir + filename);
        }

        return null;
    }

    public static void writeLine(String filename, String line) {
        //Open a file
        File file = new File(rootDir + filename);
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(line + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Cannot open " + rootDir + filename);
        }

    }


}
