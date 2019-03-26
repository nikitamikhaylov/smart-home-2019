package ru.sbt.mipt.oop.homeElement.door;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.notifications.Notifier;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;

import java.util.ArrayList;

import static ru.sbt.mipt.oop.sensor.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.sensor.SensorEventType.DOOR_OPEN;

public class DoorEventEventHandler implements EventHandler {

    private final Notifier notifier;
    private final ArrayList<SensorEventType> eventsTypes = new ArrayList<>(); //must be private

    @Override
    public void fillEventList() {
        eventsTypes.add(DOOR_OPEN);
        eventsTypes.add(DOOR_CLOSED);
    }

    @Override
    public boolean checkEventType(SensorEvent event) { //must be private
        return eventsTypes.contains(event.getType());
    }

    public DoorEventEventHandler(Notifier notifier) {
        fillEventList();
        this.notifier = notifier;
    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!checkEventType(event)) {
            return;
        }

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
