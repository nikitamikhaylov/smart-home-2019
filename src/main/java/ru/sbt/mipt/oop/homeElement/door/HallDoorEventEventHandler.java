package ru.sbt.mipt.oop.homeElement.door;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.Event.Event;
import ru.sbt.mipt.oop.Event.EventHandler;
import ru.sbt.mipt.oop.Event.EventType;
import ru.sbt.mipt.oop.command.CommandSender;
import ru.sbt.mipt.oop.command.CommandType;
import ru.sbt.mipt.oop.homeElement.Room;
import ru.sbt.mipt.oop.homeElement.light.Light;
import ru.sbt.mipt.oop.sensor.SensorCommand;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;

import java.util.ArrayList;
import java.util.List;

import static ru.sbt.mipt.oop.sensor.SensorEventType.DOOR_CLOSED;

public class HallDoorEventEventHandler implements EventHandler {

    private final CommandSender commandSender;
    private final List<SensorEventType> eventsTypes = new ArrayList<>();

    @Override
    public void fillEventList() {
        eventsTypes.add(DOOR_CLOSED);
    }

    @Override
    public boolean isNotThisEventType(Event anyEvent) {
        if (!(anyEvent instanceof SensorEvent)) {
            return true;
        }
        SensorEvent event = (SensorEvent) anyEvent;
        return !eventsTypes.contains(event.getType());
    }

    public HallDoorEventEventHandler(CommandSender sender) {
        fillEventList();
        commandSender = sender;
    }

    @Override
    public void processEvent(SmartHome smartHome, Event anyEvent) {
        if (isNotThisEventType(anyEvent)) {
            return;
        }
        SensorEvent event = (SensorEvent) anyEvent;

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
