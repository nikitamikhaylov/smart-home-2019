package ru.sbt.mipt.oop.room;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.light.Light;
import ru.sbt.mipt.oop.notifications.Notifier;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventHandler {

    private final Notifier notifier;

    public DoorEventHandler(Notifier notifier) {
        this.notifier = notifier;
    }

    public void processEvent(SmartHome smartHome, SensorEvent event) {
        // событие от двери
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_OPEN) {
                        door.setOpen(true);
                        notifier.RoomDoorOpened(room, door);
                    } else {
                        door.setOpen(false);
                        notifier.RoomDoorOpened(room, door);
                        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                        if (room.getName().equals("hall")) {
                            for (Room homeRoom : smartHome.getRooms()) {
                                for (Light light : homeRoom.getLights()) {
                                    light.setOn(false);
                                    SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                                    CommandSender.sendCommand(command);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
