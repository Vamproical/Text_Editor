package editor.menu;

import editor.TextEditor;
import editor.command.ContinueSearchCommand;
import editor.command.LoadCommand;
import editor.command.SaveCommand;
import editor.command.StartSearchCommand;
import editor.search.SearchOption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;

public class MainMenu extends JPanel {
    private static String RESOURCES_PATH = "C:\\Users\\Никита\\IdeaProjects\\Text Editor\\Text Editor\\task\\src\\icons\\";
    private static final Dimension BUTTON_DIMENSION = new Dimension(32, 32);

    private JButton saveButton;
    private JButton openButton;
    private JButton startSearchButton;
    private JButton previousMatchButton;
    private JButton nextMatchButton;
    private JPanel testPanel;

    private JCheckBox useRegExCheckbox;

    private final JTextField searchField;
    private final TextEditor editor;
    private boolean isChecked;

    public MainMenu(TextEditor editor, JTextField searchField) {
        this.editor = editor;

        this.searchField = searchField;
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setPreferredSize(new Dimension(500, 50));
        initPanel();
        initActions();
        add(testPanel);
    }

    public void initPanel() {
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        flowLayout.setVgap(1);
        testPanel = new JPanel();
        testPanel.setLayout(flowLayout);
        testPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ImageIcon saveIcon = new ImageIcon(RESOURCES_PATH + "save.png");
        ImageIcon loadIcon = new ImageIcon(RESOURCES_PATH + "load.png");
        ImageIcon searchIcon = new ImageIcon(RESOURCES_PATH + "search.png");
        ImageIcon nextIcon = new ImageIcon(RESOURCES_PATH + "next.png");
        ImageIcon prevIcon = new ImageIcon(RESOURCES_PATH + "previous.png");

        saveButton = new JButton(saveIcon);
        saveButton.setName("SaveButton");
        saveButton.setPreferredSize(BUTTON_DIMENSION);
        saveButton.setMnemonic(KeyEvent.VK_S);

        openButton = new JButton(loadIcon);
        openButton.setName("OpenButton");
        openButton.setPreferredSize(BUTTON_DIMENSION);
        openButton.setMnemonic(KeyEvent.VK_O);


        startSearchButton = new JButton(searchIcon);
        startSearchButton.setName("StartSearchButton");
        startSearchButton.setPreferredSize(BUTTON_DIMENSION);
        startSearchButton.setMnemonic(KeyEvent.VK_ENTER);


        nextMatchButton = new JButton(nextIcon);
        nextMatchButton.setName("NextMatchButton");
        nextMatchButton.setPreferredSize(BUTTON_DIMENSION);
        nextMatchButton.setMnemonic(KeyEvent.VK_L);


        previousMatchButton = new JButton(prevIcon);
        previousMatchButton.setName("PreviousMatchButton");
        previousMatchButton.setPreferredSize(BUTTON_DIMENSION);
        previousMatchButton.setMnemonic(KeyEvent.VK_J);


        useRegExCheckbox = new JCheckBox("Use regex");
        useRegExCheckbox.setName("UseRegExCheckbox");
        useRegExCheckbox.addItemListener(itemEvent -> setIsChecked(itemEvent.getStateChange() == ItemEvent.SELECTED
                ? 1 : 2));
        useRegExCheckbox.setMnemonic(KeyEvent.VK_R);
        searchField.setPreferredSize(new Dimension(300, 45));

        testPanel.add(searchField);
        testPanel.add(openButton);
        testPanel.add(saveButton);
        testPanel.add(searchField);
        testPanel.add(startSearchButton);
        testPanel.add(previousMatchButton);
        testPanel.add(nextMatchButton);
        testPanel.add(useRegExCheckbox);
    }

    private void setIsChecked(int i) {
        this.isChecked = i == 1;
    }

    public void initActions() {
        openButton.addActionListener(e ->
                SwingUtilities.invokeLater(new LoadCommand(editor)));

        saveButton.addActionListener(e ->
                SwingUtilities.invokeLater(new SaveCommand(editor)));

        startSearchButton.addActionListener(actionEvent ->
                SwingUtilities.invokeLater(
                        new StartSearchCommand(editor)));


        nextMatchButton.addActionListener(actionEvent1 ->
                SwingUtilities.invokeLater(
                        new ContinueSearchCommand(editor, SearchOption.FORWARD)));


        previousMatchButton.addActionListener(actionEvent1 ->
                SwingUtilities.invokeLater(
                        new ContinueSearchCommand(editor, SearchOption.BACKWARD)));
    }

    public void setUseRegExCheckbox() {
        useRegExCheckbox.setSelected(!isChecked);
    }

    public boolean getUseRegExCheckbox() {
        return useRegExCheckbox.isSelected();
    }
}
