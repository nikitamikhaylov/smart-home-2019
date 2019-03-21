package ru.sbt.mipt.oop.homereaderwriter;

import ru.sbt.mipt.oop.SmartHome;

import java.io.IOException;

public interface SmartHomeReader {
    SmartHome readSmartHomeState() throws IOException;
}
