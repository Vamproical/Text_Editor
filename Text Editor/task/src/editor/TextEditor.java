package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

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
        createMenu();
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

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        fileMenu.setName("MenuFile");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);

        JMenuItem loadMenuItem = new JMenuItem("Load");
        loadMenuItem.setMnemonic(KeyEvent.VK_L);
        loadMenuItem.setName("MenuLoad");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setMnemonic(KeyEvent.VK_S);
        saveMenuItem.setName("MenuSave");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setName("MenuExit");

        fileMenu.add(loadMenuItem);
        fileMenu.add(saveMenuItem);

        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        loadMenuItem.addActionListener(event -> textArea.setText(LoadSave.load(inputField.getText())));
        saveMenuItem.addActionListener(event -> LoadSave.save(textArea.getText(), inputField.getText()));
        exitMenuItem.addActionListener(event -> dispose());
    }
}
