package editor.search;

public class Match {
    private final int index;
    private final int length;

    public Match(int index, int length) {
        this.index = index;
        this.length = length;
    }

    public int getIndex() {
        return this.index;
    }

    public int getLength() {
        return this.length;
    }
}
