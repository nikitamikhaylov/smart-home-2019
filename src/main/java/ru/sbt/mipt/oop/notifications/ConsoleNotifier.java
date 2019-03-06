package ru.sbt.mipt.oop.notifications;

import ru.sbt.mipt.oop.room.Door;
import ru.sbt.mipt.oop.light.Light;
import ru.sbt.mipt.oop.Room;

public class ConsoleNotifier implements Notifier {
    @Override
    public void RoomDoorOpened(Room room, Door door) {
        System.out.println("[NOTIFICATION] Door " + door.getId() + " in room " + room.getName() + " was opened.");
    }

    @Override
    public void RoomDoorClosed(Room room, Door door) {
        System.out.println("[NOTIFICATION] Door " + door.getId() + " in room " + room.getName() + " was closed.");
    }

    @Override
    public void RoomLightOn(Room room, Light light) {
        System.out.println("[NOTIFICATION] Light " + light.getId() + " in room " + room.getName() + " was turned on.");
    }

    @Override
    public void RoomLightOff(Room room, Light light) {
        System.out.println("[NOTIFICATION] Light " + light.getId() + " in room " + room.getName() + " was turned off.");
    }
}
