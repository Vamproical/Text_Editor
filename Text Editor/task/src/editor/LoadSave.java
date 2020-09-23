package editor;

import javax.swing.*;
import java.io.*;

public class LoadSave {
    public static String load(String filename) {
        JTextArea area = new JTextArea();
        try (Reader fr = new FileReader(filename)) {
            BufferedReader reader = new BufferedReader(fr);
            area.read(reader, filename);
        } catch (IOException e) {
            System.out.println("No file found: " + filename);
        }
        return area.getText();
    }

    public static void save(String buffer, String filename) {
        File file = new File(filename);
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print(buffer);
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
    }
}
