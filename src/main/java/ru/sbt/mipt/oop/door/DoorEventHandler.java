package ru.sbt.mipt.oop.door;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.light.Light;
import ru.sbt.mipt.oop.notifications.Notifier;

import java.util.ArrayList;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements Handler{

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

    public DoorEventHandler(Notifier notifier) {
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
                            notifier.RoomDoorOpened(room, door);
                            break;
                        }
                        case DOOR_CLOSED : {
                            door.setOpen(false);
                            notifier.RoomDoorClosed(room, door);
                            // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                            // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                            if (room.getName().equals("hall")) {
                                processHallDoorEvent(smartHome);
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    private void processHallDoorEvent(SmartHome smartHome) {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                CommandSender.sendCommand(command);
            }
        }
    }


}
