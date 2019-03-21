package ru.sbt.mipt.oop.homereaderwriter;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonSmartHomeReader implements SmartHomeReader{

    private final String filename;

    public JsonSmartHomeReader(String filename) {
        this.filename = filename;
    }

    @Override
    public SmartHome readSmartHomeState() throws IOException {
        // считываем состояние дома из файла
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(filename)));
        return gson.fromJson(json, SmartHome.class);
    }
}
