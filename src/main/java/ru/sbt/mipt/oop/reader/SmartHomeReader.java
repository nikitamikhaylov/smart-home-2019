package ru.sbt.mipt.oop.reader;

import ru.sbt.mipt.oop.SmartHome;

import java.io.IOException;

public interface SmartHomeReader {
    SmartHome readSmartHomeState() throws IOException;
}
