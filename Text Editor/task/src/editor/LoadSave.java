package editor;

import javax.swing.*;
import java.io.*;

public class LoadSave {
    public static String load(File file) {
        JTextArea area = new JTextArea();
        try (Reader fr = new FileReader(file)) {
            BufferedReader reader = new BufferedReader(fr);
            area.read(reader, file);
        } catch (IOException e) {
            System.out.println("No file found: " + file);
        }
        return area.getText();
    }

    public static void save(String buffer, File file) {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print(buffer);
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
    }
}
