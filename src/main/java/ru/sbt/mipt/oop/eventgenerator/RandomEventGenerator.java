package ru.sbt.mipt.oop.eventgenerator;

import ru.sbt.mipt.oop.alarm.AlarmEvent;
import ru.sbt.mipt.oop.alarm.AlarmEventType;
import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;

public class RandomEventGenerator implements EventGenerator {

    public Event getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of eventgenerator stream
        if (Math.random() < 0.5) {
            SensorEventType sensorEventType = SensorEventType.values()[(int) (SensorEventType.COUNT * Math.random())];
            String objectId = "" + ((int) (10 * Math.random()));
            return new SensorEvent(sensorEventType, objectId);
        } else {
            AlarmEventType alarmEventType = AlarmEventType.values()[(int) (AlarmEventType.COUNT * Math.random())];
            return new AlarmEvent(alarmEventType, "1488");
        }

    }
}