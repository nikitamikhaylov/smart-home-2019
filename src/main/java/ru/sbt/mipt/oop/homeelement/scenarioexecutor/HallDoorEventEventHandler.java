package ru.sbt.mipt.oop.homeelement.scenarioexecutor;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.event.EventHandler;
import ru.sbt.mipt.oop.command.CommandSender;
import ru.sbt.mipt.oop.command.CommandType;
import ru.sbt.mipt.oop.homeelement.Door;
import ru.sbt.mipt.oop.homeelement.Room;
import ru.sbt.mipt.oop.homeelement.Light;
import ru.sbt.mipt.oop.sensor.SensorCommand;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;

import java.util.Arrays;
import java.util.List;

import static ru.sbt.mipt.oop.sensor.SensorEventType.DOOR_CLOSED;

public class HallDoorEventEventHandler implements EventHandler {

    private final CommandSender commandSender;
    private final SmartHome smartHome;
    private final List<SensorEventType> eventsTypes = Arrays.asList(DOOR_CLOSED);

    public HallDoorEventEventHandler( SmartHome smartHome, CommandSender commandSender) {
        this.commandSender = commandSender;
        this.smartHome = smartHome;
    }

    private boolean isNotThisEventType(Event anyEvent) {
        if (!(anyEvent instanceof SensorEvent)) {
            return true;
        }
        SensorEvent event = (SensorEvent) anyEvent;
        return !eventsTypes.contains(event.getType());
    }

    @Override
    public void processEvent(Event anyEvent) {
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
