package editor.search;

import java.util.ArrayDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchByRegExp extends Searcher {
    public SearchByRegExp(String pattern, String text, ArrayDeque<Match> matches) {
        super(pattern, text, matches);
    }

    @Override
    public void findAll() {
        Pattern regExPattern = Pattern.compile(this.pattern);
        Matcher matcher = regExPattern.matcher(this.text);
        this.matches.clear();
        while (matcher.find()) {
            this.matches.push(new Match(matcher.start(),
                    matcher.end() - matcher.start()));
        }
    }
}
