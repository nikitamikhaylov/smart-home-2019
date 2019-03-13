package ru.sbt.mipt.oop.door;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.command.CommandSender;
import ru.sbt.mipt.oop.command.CommandType;
import ru.sbt.mipt.oop.light.Light;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

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
        // событие от двери
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                    // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                    if (room.getName().equals("hall")) {
                        for (Light light : room.getLights()) {
                            light.setOn(false);
                            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                            commandSender.sendCommand(command);
                        }
                    }
                }
            }
        }
    }
}
