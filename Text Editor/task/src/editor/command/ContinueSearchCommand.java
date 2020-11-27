package editor.command;

import editor.TextEditor;
import editor.search.SearchOption;

public class ContinueSearchCommand extends Command{
    private TextEditor textEditor;
    private SearchOption option;

    public ContinueSearchCommand(TextEditor editor, SearchOption option) {
        this.textEditor = editor;
        this.option = option;
    }

    @Override
    public void execute() {
        this.textEditor.startSearch(this.option);
    }
}
