package ru.sbt.mipt.oop.EventGenerator;

import ru.sbt.mipt.oop.sensor.SensorEvent;

public interface EventGenerator {
    SensorEvent getNextSensorEvent();
}
