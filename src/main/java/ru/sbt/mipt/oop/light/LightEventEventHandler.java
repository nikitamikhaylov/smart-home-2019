package ru.sbt.mipt.oop.light;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.notifications.Notifier;
import ru.sbt.mipt.oop.Room;

import java.util.ArrayList;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

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
        // событие от источника света
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    SensorEventType eventType = event.getType();
                    switch (eventType) {
                        case LIGHT_ON : {
                            light.setOn(true);
                            notifier.notifyAbout("[NOTIFICATION] Light " + light.getId() + " in door " + room.getName() + " was turned on.");
                            break;
                        }
                        case LIGHT_OFF : {
                            light.setOn(false);
                            notifier.notifyAbout("[NOTIFICATION] Light " + light.getId() + " in door " + room.getName() + " was turned off.");
                            break;
                        }
                    }
                }
            }
        }
    }
}
