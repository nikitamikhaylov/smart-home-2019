package ru.sbt.mipt.oop.sensor;

import ru.sbt.mipt.oop.Event.EventType;

public enum SensorEventType implements EventType {
    LIGHT_ON, LIGHT_OFF, DOOR_OPEN, DOOR_CLOSED;

    public static int COUNT = SensorEventType.values().length;
}
