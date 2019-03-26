package ru.sbt.mipt.oop.notifications;

public class ConsoleNotifier implements Notifier {
    @Override
    public void notifyAbout(String message) {
        System.out.println(message);
    }
}
