package ru.sbt.mipt.oop.notifications;

public class SMSNotifier implements Notifier {
    @Override
    public void notifyAbout(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

