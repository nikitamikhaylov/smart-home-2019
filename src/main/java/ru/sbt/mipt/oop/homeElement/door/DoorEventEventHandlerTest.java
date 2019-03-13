package ru.sbt.mipt.oop.homeElement.door;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
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

class DoorEventEventHandlerTest {
    @Test
    public void testDoorOpenProcessEvent() throws IOException {
        SmartHomeReader smartHomeReader = new JsonSmartHomeReader("smart-home-1.json");
        SmartHome smartHome = smartHomeReader.readSmartHomeState();
        DoorEventEventHandler handler = new DoorEventEventHandler(new ConsoleNotifier());
        handler.processEvent(smartHome, new SensorEvent(SensorEventType.DOOR_CLOSED, "1"));

        smartHome.execute(c -> {
            if (c instanceof Door) {
                Door door = (Door) c;
                if (door.getId().equals("1")) {
                    assertFalse(door.isOpen());
                }
            }
        });
    }

    @Test
    public void testDoorClosedProcessEvent() throws IOException {
        SmartHomeReader smartHomeReader = new JsonSmartHomeReader("smart-home-1.json");
        SmartHome smartHome = smartHomeReader.readSmartHomeState();
        DoorEventEventHandler handler = new DoorEventEventHandler(new ConsoleNotifier());
        handler.processEvent(smartHome, new SensorEvent(SensorEventType.DOOR_OPEN, "2"));

        smartHome.execute(c -> {
            if (c instanceof Door) {
                Door door = (Door) c;
                if (door.getId().equals("2")) {
                    assertTrue(door.isOpen());
                }
            }
        });
    }
}