package ru.sbt.mipt.oop.homeElement.light;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.Event.Event;
import ru.sbt.mipt.oop.Event.EventHandler;
import ru.sbt.mipt.oop.notifications.Notifier;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;

import java.util.ArrayList;
import java.util.List;

import static ru.sbt.mipt.oop.sensor.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.sensor.SensorEventType.LIGHT_ON;

public class LightEventEventHandler implements EventHandler {

    private final Notifier notifier;
    private final List<SensorEventType> eventsTypes = new ArrayList<>();

    @Override
    public void fillEventList() {
        eventsTypes.add(LIGHT_ON);
        eventsTypes.add(LIGHT_OFF);
    }

    @Override
    public boolean isNotThisEventType(Event anyEvent) {
        if (!(anyEvent instanceof SensorEvent)) {
            return true;
        }
        SensorEvent event = (SensorEvent) anyEvent;
        return !eventsTypes.contains(event.getType());
    }

    public LightEventEventHandler(Notifier notifier) {
        this.notifier = notifier;
        fillEventList();
    }

    @Override
    public void processEvent(SmartHome smartHome, Event anyEvent) {
        if (isNotThisEventType(anyEvent)) {
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
