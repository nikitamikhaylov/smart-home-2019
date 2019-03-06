package ru.sbt.mipt.oop.light;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.notifications.Notifier;
import ru.sbt.mipt.oop.room.Room;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventHandler {
    private final Notifier notifier;

    public LightEventHandler(Notifier notifier) {
        this.notifier = notifier;
    }

    public void processEvent(SmartHome smartHome, SensorEvent event) {
        // событие от источника света
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    if (event.getType() == LIGHT_ON) {
                        light.setOn(true);
                        notifier.RoomLightOn(room, light);
                    } else {
                        light.setOn(false);
                        notifier.RoomLightOn(room, light);
                    }
                }
            }
        }
    }
}
