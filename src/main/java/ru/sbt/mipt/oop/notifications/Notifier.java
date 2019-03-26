package ru.sbt.mipt.oop.notifications;

import ru.sbt.mipt.oop.door.Door;
import ru.sbt.mipt.oop.light.Light;
import ru.sbt.mipt.oop.Room;

public interface Notifier {
    public void notifyAbout(String message);
}
