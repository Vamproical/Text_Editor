package editor;

public class Settings {
    private final String font;
    private final int size;

    public Settings(String f, int size) {
        font = f;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public String getFont() {
        return font;
    }
}
