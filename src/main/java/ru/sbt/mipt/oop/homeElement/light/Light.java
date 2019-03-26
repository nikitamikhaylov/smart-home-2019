package ru.sbt.mipt.oop.homeElement.light;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.homeElement.HomeElement;

public class Light implements HomeElement {
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public String getId() {
        return id;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
    }
}
