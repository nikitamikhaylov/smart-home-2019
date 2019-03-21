package ru.sbt.mipt.oop.homeelement.updatehomemodel;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.event.EventHandler;
import ru.sbt.mipt.oop.homeelement.Door;
import ru.sbt.mipt.oop.notifications.Notifier;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;

import java.util.Arrays;
import java.util.List;

import static ru.sbt.mipt.oop.sensor.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.sensor.SensorEventType.DOOR_OPEN;

public class DoorEventEventHandler implements EventHandler {

    private final Notifier notifier;
    private final SmartHome smartHome;
    private final List<SensorEventType> eventsTypes = Arrays.asList(DOOR_OPEN, DOOR_CLOSED);

    private boolean isNotThisEventType(Event anyEvent) {
        if (!(anyEvent instanceof SensorEvent)) {
            return true;
        }
        SensorEvent event = (SensorEvent) anyEvent;
        return !eventsTypes.contains(event.getType());
    }

    public DoorEventEventHandler(SmartHome smartHome, Notifier notifier) {
        this.notifier = notifier;
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent(Event anyEvent) {
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
