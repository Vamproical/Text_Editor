package editor.command;

import editor.TextEditor;
import editor.menu.MenuBar;

public class SaveCommand extends Command {
    private final TextEditor editor;

    public SaveCommand(TextEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.saveToFile();
    }
}
