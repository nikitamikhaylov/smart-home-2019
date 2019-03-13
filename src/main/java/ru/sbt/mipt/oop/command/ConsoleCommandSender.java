package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.sensor.SensorCommand;

public class ConsoleCommandSender implements CommandSender{
    public void sendCommand(SensorCommand command) {
        System.out.println("[COMMAND] " + command);
    }
}
