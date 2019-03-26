package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.SensorEvent;

public interface EventGenerator {
    SensorEvent getNextSensorEvent();
}
