package editor.search;

import editor.TextEditor;

import javax.swing.*;

public class StartSearch extends SwingWorker<Integer, String> {

    private SearchOption option;
    private TextEditor textEditor;
    public StartSearch(TextEditor textEditor) {
        this.textEditor = textEditor;
    }
    @Override
    protected Integer doInBackground() {
        textEditor.startSearch(this.option);
        return 0;
    }

    public void setOption(SearchOption option) {
        this.option = option;
    }
}