package ru.sbt.mipt.oop.homeElement.door;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.Event.Event;
import ru.sbt.mipt.oop.Event.EventHandler;
import ru.sbt.mipt.oop.notifications.Notifier;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;

import java.util.ArrayList;
import java.util.List;

import static ru.sbt.mipt.oop.sensor.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.sensor.SensorEventType.DOOR_OPEN;

public class DoorEventEventHandler implements EventHandler {

    private final Notifier notifier;
    private final List<SensorEventType> eventsTypes = new ArrayList<>();

    @Override
    public void fillEventList() {
        eventsTypes.add(DOOR_OPEN);
        eventsTypes.add(DOOR_CLOSED);
    }

    @Override
    public boolean isNotThisEventType(Event anyEvent) { //must be private
        if (!(anyEvent instanceof SensorEvent)) {
            return true;
        }
        SensorEvent event = (SensorEvent) anyEvent;
        return !eventsTypes.contains(event.getType());
    }

    public DoorEventEventHandler(Notifier notifier) {
        fillEventList();
        this.notifier = notifier;
    }

    @Override
    public void processEvent(SmartHome smartHome, Event anyEvent) {
        if (isNotThisEventType(anyEvent)) {
            return;
        }
        SensorEvent event = (SensorEvent) anyEvent;

        smartHome.execute(object -> {
            if (object instanceof Door) {
                Door door = (Door) object;
                if (door.getId().equals(event.getObjectId())) {
                    SensorEventType eventType = event.getType();
                    switch (eventType) {
                        case DOOR_OPEN : {
                            door.setOpen(true);
                            notifier.notifyAbout("[NOTIFICATION] Door " + door.getId() + " was opened.");
                            break;
                        }
                        case DOOR_CLOSED : {
                            door.setOpen(false);
                            notifier.notifyAbout("[NOTIFICATION] Door " + door.getId() + " was closed.");
                            break;
                        }
                    }
                }
            }
        });
    }

}
