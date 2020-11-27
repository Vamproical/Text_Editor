package editor.search;

import javax.swing.*;
import java.util.ArrayDeque;

public abstract class Searcher extends SwingWorker<Integer, String> {
    protected String pattern;
    protected String text;
    protected ArrayDeque<Match> matches;

    abstract protected void findAll();

    public Searcher(String pattern, String text, ArrayDeque<Match> matches) {
        this.pattern = pattern;
        this.text = text;
        this.matches = matches;
    }

    @Override
    protected Integer doInBackground() {
        this.findAll();
        return 0;
    }
}
