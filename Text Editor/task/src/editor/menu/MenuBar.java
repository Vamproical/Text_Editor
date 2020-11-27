package editor.menu;

import editor.TextEditor;
import editor.command.ContinueSearchCommand;
import editor.command.LoadCommand;
import editor.command.SaveCommand;
import editor.command.StartSearchCommand;
import editor.search.SearchOption;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    private final TextEditor editor;

    private final MainMenu mainMenu;

    public MenuBar(TextEditor editor, MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        this.editor = editor;
        init();
    }

    public void init() {
        JMenu mFile = new JMenu("File");
        mFile.setName("MenuFile");

        JMenuItem load = new JMenuItem("Open");
        load.setName("MenuOpen");
        load.addActionListener(e -> SwingUtilities.invokeLater(new LoadCommand(editor)));

        JMenuItem save = new JMenuItem("Save");
        save.setName("MenuSave");
        save.addActionListener(e -> SwingUtilities.invokeLater(new SaveCommand(editor)));

        JMenuItem exit = new JMenuItem("Exit");
        exit.setName("MenuExit");
        exit.addActionListener(e -> editor.dispose());

        mFile.add(load);
        mFile.add(save);
        mFile.addSeparator();
        mFile.add(exit);

        add(mFile);

        initSearch();
    }

    private void initSearch() {
        JMenu search = new JMenu("Search");
        search.setName("MenuSearch");
        JMenuItem startSearch = new JMenuItem("Start search");
        startSearch.setName("MenuStartSearch");
        JMenuItem prevSearch = new JMenuItem("Previous search");
        prevSearch.setName("MenuPreviousMatch");
        JMenuItem nextSearch = new JMenuItem("Next match");
        nextSearch.setName("MenuNextMatch");
        JMenuItem useRegexp = new JMenuItem("Use regular expressions");
        useRegexp.setName("MenuUseRegExp");

        useRegexp.addActionListener(e -> mainMenu.setUseRegExCheckbox());

        startSearch.addActionListener(actionEvent ->
            SwingUtilities.invokeLater(
                    new StartSearchCommand(editor)));

        nextSearch.addActionListener(actionEvent1 ->
                SwingUtilities.invokeLater(
                new ContinueSearchCommand(editor, SearchOption.FORWARD)));

        prevSearch.addActionListener(actionEvent1 ->
                SwingUtilities.invokeLater(
                new ContinueSearchCommand(editor, SearchOption.BACKWARD)));

        search.add(startSearch);
        search.add(prevSearch);
        search.add(nextSearch);
        search.add(useRegexp);

        add(search);
    }
}
