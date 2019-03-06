package ru.sbt.mipt.oop;

public class CommandSender {
    public static void sendCommand(SensorCommand command) {
        System.out.println("[COMMAND] " + command);
    }
}
