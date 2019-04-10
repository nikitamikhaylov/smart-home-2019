package ru.sbt.mipt.oop.adapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;

public class CCSensorEventToNativeSensorEventAdapter{

    public static SensorEvent adapt(CCSensorEvent ccSensorEvent) {
        return new SensorEvent(convertType(ccSensorEvent), ccSensorEvent.getObjectId());
    }

    private static SensorEventType convertType(CCSensorEvent ccSensorEvent) {
        switch (ccSensorEvent.getEventType()) {
            case "LightIsOn":
                return SensorEventType.LIGHT_ON;
            case "LightIsOff":
                return SensorEventType.LIGHT_OFF;
            case "DoorIsOpen":
                return SensorEventType.DOOR_OPEN;
            case "DoorIsClosed":
                return SensorEventType.DOOR_CLOSED;
            default:
                throw new RuntimeException("Unknown EventType");
        }
    }
}