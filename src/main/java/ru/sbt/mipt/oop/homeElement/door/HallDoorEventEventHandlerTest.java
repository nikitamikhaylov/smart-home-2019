package ru.sbt.mipt.oop.homeElement.door;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.command.ConsoleCommandSender;
import ru.sbt.mipt.oop.homeElement.Room;
import ru.sbt.mipt.oop.homeElement.light.Light;
import ru.sbt.mipt.oop.homeElement.light.LightEventEventHandler;
import ru.sbt.mipt.oop.notifications.ConsoleNotifier;
import ru.sbt.mipt.oop.reader.JsonSmartHomeReader;
import ru.sbt.mipt.oop.reader.SmartHomeReader;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HallDoorEventEventHandlerTest {

    @Test
    public void testHallDoorClosedProcessEvent() throws IOException {
        SmartHomeReader smartHomeReader = new JsonSmartHomeReader("smart-home-1.json");
        SmartHome smartHome = smartHomeReader.readSmartHomeState();

        LightEventEventHandler lightHandler = new LightEventEventHandler(new ConsoleNotifier());
        lightHandler.processEvent(smartHome, new SensorEvent(SensorEventType.LIGHT_ON, "7"));
        lightHandler.processEvent(smartHome, new SensorEvent(SensorEventType.LIGHT_ON, "8"));
        lightHandler.processEvent(smartHome, new SensorEvent(SensorEventType.LIGHT_ON, "9"));

        HallDoorEventEventHandler handler = new HallDoorEventEventHandler(new ConsoleCommandSender());
        handler.processEvent(smartHome, new SensorEvent(SensorEventType.DOOR_CLOSED, "4"));

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals("4")) {
                    assertFalse(door.isOpen());
                }
            }
        }

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals("7") || light.getId().equals("8") || light.getId().equals("9")) {
                    assertFalse(light.isOn());
                }
            }
        }
    }
}