package editor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class SaveSettings {
    public void save(String font, int size) {
        try (Writer writer = new FileWriter("settings.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(new Settings(font,size), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Settings read() {
        Gson gson = new Gson();
        Settings settings;
        try {
            BufferedReader br = new BufferedReader(new FileReader("settings.json"));
            settings = gson.fromJson(br, Settings.class);
            return settings;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
