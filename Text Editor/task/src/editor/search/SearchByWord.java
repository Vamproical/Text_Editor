package editor.search;

import java.util.ArrayDeque;

public class SearchByWord extends Searcher {
    public SearchByWord(String pattern, String text, ArrayDeque<Match> matches) {
        super(pattern, text, matches);
    }

    @Override
    protected void findAll() {
        int index;
        int begin = 0;
        this.matches.clear();
        do {
            index = this.text.indexOf(this.pattern, begin);
            if (index < 0) break;
            begin = index + this.pattern.length();
            System.out.print(begin + " " + pattern.length());
            this.matches.push(new Match(index, this.pattern.length()));
        } while (true);
    }
}


