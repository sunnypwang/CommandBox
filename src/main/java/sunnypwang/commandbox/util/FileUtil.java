package sunnypwang.commandbox.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileUtil {

    public static final String rootDir = "plugins/CommandBox/";

    public static void initialize() {
        try {
            Files.createDirectories(Paths.get(rootDir)); //Make a directory if not exist
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File loadFile(String filename){
        String filepath = Paths.get(rootDir, filename).toString();
        File file = new File(filepath);
        try {
            if (file.createNewFile()) { //create new file, do nothing if exists
                System.out.println("File created: " + file.getPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static File loadFile(String subDir, String filename){
        Path filepath = Paths.get(rootDir, subDir, filename);
        try {
            if (Files.notExists(filepath)){
                Files.createDirectories(Paths.get(filepath.getParent().toString()));
                Files.createFile(filepath);
            }
            return new File(filepath.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> readFile(String subDir, String filename) {
        File file = loadFile(subDir, filename);
        try {
            return Files.readAllLines(Paths.get(file.getPath()));
        } catch (IOException e) {
            System.out.println("Cannot open " + file.getPath());
        }

        return null;
    }

    public static void writeLine(String subDir, String filename, String line) {
        //Open a file
        File file = loadFile(subDir, filename);
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(line + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Cannot open " + rootDir + filename);
        }

    }


}
