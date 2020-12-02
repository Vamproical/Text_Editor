package editor.menu;

import editor.TextEditor;

import javax.swing.*;

public class ApplicationRunner {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.invokeLater(TextEditor::new);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
