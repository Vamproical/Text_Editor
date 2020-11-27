package editor.command;

import editor.TextEditor;
import editor.search.SearchByRegExp;
import editor.search.SearchByWord;
import editor.search.SearchOption;
import editor.search.StartSearch;

import javax.swing.*;

public class StartSearchCommand extends Command{
    private final TextEditor textEditor;

    public StartSearchCommand(TextEditor editor) {
        this.textEditor = editor;
    }
    @Override
    public void execute() {
        Thread thread = null;
        if (textEditor.getRegExp()) {
            try {
                thread = new Thread(new SearchByRegExp(this.textEditor.getSearchField(),
                        this.textEditor.getText(),
                        this.textEditor.getMatches()));
                SwingUtilities.invokeLater(thread);
            } catch (Exception e) {
                System.out.println("Something went wrong");
            }
        } else {
            try {
                thread = new Thread(new SearchByWord(this.textEditor.getSearchField(),
                        this.textEditor.getText(),
                        this.textEditor.getMatches()));
                SwingUtilities.invokeLater(thread);
            } catch (Exception e) {
                System.out.println("Something went wrong");
            }
        }
        if (thread == null) return;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.textEditor.saveSearchResults();
        StartSearch searchThread = new StartSearch(textEditor);
        searchThread.setOption(SearchOption.START);
        SwingUtilities.invokeLater(searchThread);
    }
}
