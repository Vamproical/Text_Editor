package editor;

import javax.swing.*;
import java.awt.*;

public class TextEditor extends JFrame {
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JButton saveButton;
    private JButton loadButton;
    private JTextField inputField;
    private JPanel panel;

    public TextEditor() {
        setTitle("Text Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);
        createButton();
        createTextArea();
        Container c = getContentPane();
        c.add(panel, BorderLayout.NORTH);
        c.add(scrollPane, BorderLayout.CENTER);
    }

    private void createButton() {
        panel = new JPanel(new BorderLayout());

        inputField = new JTextField();
        inputField.setName("FilenameField");

        saveButton = new JButton("Save");
        saveButton.setName("SaveButton");

        loadButton = new JButton("Load");
        loadButton.setName("LoadButton");

        loadButton.addActionListener(actionEvent -> textArea.setText(LoadSave.load(inputField.getText())));
        saveButton.addActionListener(actionEvent -> LoadSave.save(textArea.getText(), inputField.getText()));

        panel.add(inputField, BorderLayout.CENTER);
        panel.add(saveButton, BorderLayout.LINE_START);
        panel.add(loadButton, BorderLayout.LINE_END);
    }

    private void createTextArea() {
        textArea = new JTextArea();
        textArea.setName("TextArea");
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setName("ScrollPane");
    }
}
