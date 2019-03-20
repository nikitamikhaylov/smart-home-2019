package ru.sbt.mipt.oop.EventGenerator;

import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;

public class RandomEventGenerator implements EventGenerator {

    public SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of EventGenerator stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (SensorEventType.COUNT * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
