package ru.sbt.mipt.oop.door;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.notifications.Notifier;

import java.util.ArrayList;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

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
        // событие от двери
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    SensorEventType eventType = event.getType();
                    switch (eventType) {
                        case DOOR_OPEN : {
                            door.setOpen(true);
                            notifier.notifyAbout("[NOTIFICATION] Door " + door.getId() + " in door " + room.getName() + " was opened.");
                            break;
                        }
                        case DOOR_CLOSED : {
                            door.setOpen(false);
                            notifier.notifyAbout("[NOTIFICATION] Door " + door.getId() + " in door " + room.getName() + " was closed.");
                            break;
                        }
                    }
                }
            }
        }
    }

}
