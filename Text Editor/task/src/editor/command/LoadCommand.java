package editor.command;

import editor.TextEditor;

public class LoadCommand extends Command {
    private final TextEditor editor;

    public LoadCommand(TextEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.loadFromFile();
    }
}
