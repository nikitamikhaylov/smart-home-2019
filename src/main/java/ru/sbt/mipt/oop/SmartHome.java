package ru.sbt.mipt.oop;


import ru.sbt.mipt.oop.homeElement.HomeElement;
import ru.sbt.mipt.oop.homeElement.Room;
import ru.sbt.mipt.oop.homeElement.door.Door;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements HomeElement {
    private final Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public void execute(Action action) {
        action.execute(this);
        rooms.forEach(c -> c.execute(action));
    }
}
