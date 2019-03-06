package ru.sbt.mipt.oop.notifications;

import ru.sbt.mipt.oop.room.Door;
import ru.sbt.mipt.oop.light.Light;
import ru.sbt.mipt.oop.Room;

public interface Notifier {
    public void RoomDoorOpened(Room room, Door door);
    public void RoomDoorClosed(Room room, Door door);
    public void RoomLightOn(Room room, Light light);
    public void RoomLightOff(Room room, Light light);
}
