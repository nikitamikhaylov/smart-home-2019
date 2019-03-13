package ru.sbt.mipt.oop.homeElement;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.homeElement.light.Light;
import ru.sbt.mipt.oop.homeElement.door.Door;

import java.util.Collection;

public class Room implements HomeElement {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
        lights.forEach((c) -> c.execute(action));
        doors.forEach((c) -> c.execute(action));
    }
}
