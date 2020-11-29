package editor.command;

import editor.TextEditor;
import editor.font.SetFont;

public class FontCommand extends Command{
    private final TextEditor editor;
    public FontCommand(TextEditor editor) {
        this.editor = editor;
    }
    @Override
    public void execute() {
        SetFont font = new SetFont(editor);
        font.setVisible(true);
    }
}
