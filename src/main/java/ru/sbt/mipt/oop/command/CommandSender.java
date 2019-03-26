package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.SensorCommand;

public interface CommandSender {
    public void sendCommand(SensorCommand command);
}
