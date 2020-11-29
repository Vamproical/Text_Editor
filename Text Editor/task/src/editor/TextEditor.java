package editor;

import editor.font.SetFont;
import editor.menu.MainMenu;
import editor.menu.MenuBar;
import editor.search.Match;
import editor.search.SearchOption;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayDeque;

public class TextEditor extends JFrame {
    private JTextArea textArea;
    private final JFileChooser fileChooser;
    private final ArrayDeque<Match> matches;
    private ArrayDeque<Match> initSearch;
    private final MainMenu mainMenu;
    private final JTextField searchField;

    public TextEditor() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(650, 650);
        setTitle("TextEditor");
        setMaximumSize(new Dimension(650, 650));
        createTextArea();
        WindowsListener window = new WindowsListener(this);
        addWindowListener(window);
        matches = new ArrayDeque<>();
        initSearch = new ArrayDeque<>();
        this.fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        searchField = new JTextField();
        mainMenu = new MainMenu(this, searchField);
        MenuBar menuBar = new MenuBar(this, mainMenu);
        setJMenuBar(menuBar);
        add(mainMenu, BorderLayout.NORTH);
        setVisible(true);
    }

    private void createTextArea() {
        textArea = new JTextArea();
        textArea.setName("TextArea");
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.copy();
        add(textArea);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setName("ScrollPane");
        scrollPane.setViewportView(textArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public void clearText() {
        textArea.setText("");
    }

    private void appendText(String load) {
        textArea.setText(load);
    }

    public synchronized void loadFromFile() {
        fileChooser.setVisible(true);
        int ret = fileChooser.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            clearText();
            appendText(LoadSave.load(file));
        }
        fileChooser.setVisible(false);
    }

    public synchronized void saveToFile() {
        fileChooser.setVisible(true);
        fileChooser.setDialogTitle("Сохранение файла");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File filename = fileChooser.getSelectedFile();
            LoadSave.save(textArea.getText(), filename);
        }
        fileChooser.setVisible(false);
    }

    public boolean getRegExp() {
        return mainMenu.getUseRegExCheckbox();
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public String getText() {
        return textArea.getText();
    }

    public String getSearchField() {
        return searchField.getText();
    }

    public ArrayDeque<Match> getMatches() {
        return this.matches;
    }

    public void saveSearchResults() {
        this.initSearch = this.matches;
    }

    public void startSearch(SearchOption option) {
        int index = 0;
        int wordLength = 0;
        switch (option) {
            case FORWARD:
                if (this.textArea.getCaretPosition()
                        == this.matches.getLast().getIndex()
                        + this.matches.getLast().getLength()) {
                    this.matches.addFirst(this.matches.removeLast());
                }
                index = this.matches.getLast().getIndex();
                wordLength = this.matches.getLast().getLength();
                this.matches.addFirst(this.matches.removeLast());
                break;
            case BACKWARD:
                if (this.textArea.getCaretPosition()
                        == this.matches.getFirst().getIndex()
                        + this.matches.getFirst().getLength()) {
                    this.matches.addLast(this.matches.removeFirst());
                }
                index = this.matches.getFirst().getIndex();
                wordLength = this.matches.getFirst().getLength();
                this.matches.addLast(this.matches.removeFirst());
                break;
            case START:
                index = this.initSearch.getLast().getIndex();
                wordLength = this.initSearch.getLast().getLength();
        }
        this.textArea.setCaretPosition(index + wordLength);
        this.textArea.select(index, index + wordLength);
        this.textArea.grabFocus();
    }

}
