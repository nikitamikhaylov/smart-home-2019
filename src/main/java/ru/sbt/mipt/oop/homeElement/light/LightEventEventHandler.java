package ru.sbt.mipt.oop.homeElement.light;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.notifications.Notifier;
import ru.sbt.mipt.oop.homeElement.Room;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;

import java.util.ArrayList;

import static ru.sbt.mipt.oop.sensor.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.sensor.SensorEventType.LIGHT_ON;

public class LightEventEventHandler implements EventHandler {
    private final Notifier notifier;
    private final ArrayList<SensorEventType> eventsTypes = new ArrayList<>();

    @Override
    public void fillEventList() {
        eventsTypes.add(LIGHT_ON);
        eventsTypes.add(LIGHT_OFF);
    }

    @Override
    public boolean checkEventType(SensorEvent event) {
        return eventsTypes.contains(event.getType());
    }

    public LightEventEventHandler(Notifier notifier) {
        this.notifier = notifier;
        fillEventList();
    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!checkEventType(event)) {
            return;
        }

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
