package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.SmartHome;

public interface RemoteControlCommand {
    void execute();
    String getID();
}
