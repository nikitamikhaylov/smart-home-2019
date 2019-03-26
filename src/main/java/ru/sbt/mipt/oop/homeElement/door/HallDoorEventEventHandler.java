package ru.sbt.mipt.oop.homeElement.door;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.command.CommandSender;
import ru.sbt.mipt.oop.command.CommandType;
import ru.sbt.mipt.oop.homeElement.Room;
import ru.sbt.mipt.oop.homeElement.light.Light;
import ru.sbt.mipt.oop.sensor.SensorCommand;
import ru.sbt.mipt.oop.sensor.SensorEvent;

import static ru.sbt.mipt.oop.sensor.SensorEventType.DOOR_CLOSED;

public class HallDoorEventEventHandler implements EventHandler {
    private final CommandSender commandSender;

    @Override
    public void fillEventList() {
        eventsTypes.add(DOOR_CLOSED);
    }

    @Override
    public boolean checkEventType(SensorEvent event) {
        return eventsTypes.contains(event.getType());
    }

    public HallDoorEventEventHandler(CommandSender sender) {
        fillEventList();
        commandSender = sender;
    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!checkEventType(event)) {
            return;
        }

        smartHome.execute(object -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals("hall")) {
                    boolean hasHallDoor = false;
                    for (Door door: room.getDoors()) {
                        if (door.getId().equals(event.getObjectId())) {
                            hasHallDoor = true;
                        }
                    }
                    if (!hasHallDoor) {
                        return;
                    }

                    for (Room roomIter: smartHome.getRooms()) {
                        for (Light light: roomIter.getLights()) {
                            light.setOn(false);
                            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                            commandSender.sendCommand(command);
                        }
                    }

                }
            }
        });
    }
}
