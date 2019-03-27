package ru.sbt.mipt.oop.homeelement.updatehomemodel;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.event.EventHandler;
import ru.sbt.mipt.oop.homeelement.Light;
import ru.sbt.mipt.oop.notifications.Notifier;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;

import java.util.Arrays;
import java.util.List;

import static ru.sbt.mipt.oop.sensor.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.sensor.SensorEventType.LIGHT_ON;

public class LightEventHandler implements EventHandler {
    private final Notifier notifier;
    private final SmartHome smartHome;
    private final List<SensorEventType> eventsTypes = Arrays.asList(LIGHT_ON, LIGHT_OFF);

    public LightEventHandler(SmartHome smartHome, Notifier notifier) {
        this.notifier = notifier;
        this.smartHome = smartHome;
    }

    private boolean isLightEventType(Event anyEvent) {
        if (!(anyEvent instanceof SensorEvent)) {
            return false;
        }
        SensorEvent event = (SensorEvent) anyEvent;
        return eventsTypes.contains(event.getType());
    }

    @Override
    public void processEvent(Event anyEvent) {
        if (!isLightEventType(anyEvent)) {
            return;
        }
        SensorEvent event = (SensorEvent) anyEvent;

        smartHome.execute(object -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                if (light.getId().equals(event.getObjectId())) {
                    SensorEventType eventType = event.getType();
                    switch (eventType) {
                        case LIGHT_ON : {
                            light.setOn(true);
                            notifier.notifyAbout("[NOTIFICATION] Light " + light.getId() + " was turned on.");
                            break;
                        }
                        case LIGHT_OFF : {
                            light.setOn(false);
                            notifier.notifyAbout("[NOTIFICATION] Light " + light.getId() + " was turned off.");
                            break;
                        }
                    }
                }
            }
        });
    }
}
