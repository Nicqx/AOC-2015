package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    String resourcePath;

    public FileReader(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String fileReaderString() {
        String text = "";
        try (Scanner scanner = new Scanner(new File(resourcePath))) {
            text = scanner.nextLine();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        return text;
    }

    public ArrayList<String> fileReaderArrayList() {
        ArrayList<String> result = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(resourcePath))) {
            while (scanner.hasNext()) {
                result.add(scanner.nextLine());
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        return result;
    }
}
