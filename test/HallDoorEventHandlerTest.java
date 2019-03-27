import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.command.ConsoleCommandSender;
import ru.sbt.mipt.oop.homeelement.Door;
import ru.sbt.mipt.oop.homeelement.scenarioexecutor.HallDoorEventEventHandler;
import ru.sbt.mipt.oop.homeelement.Light;
import ru.sbt.mipt.oop.homeelement.updatehomemodel.LightEventHandler;
import ru.sbt.mipt.oop.notifications.ConsoleNotifier;
import ru.sbt.mipt.oop.homereaderwriter.JsonSmartHomeReader;
import ru.sbt.mipt.oop.homereaderwriter.SmartHomeReader;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HallDoorEventHandlerTest {

    private SmartHome readSmartHomeFromJSON() throws IOException {
        SmartHomeReader smartHomeReader = new JsonSmartHomeReader("smart-home-1.json");
        return smartHomeReader.readSmartHomeState();
    }

    @Test
    public void testHallDoorClosedProcessEvent() throws IOException {
        SmartHome smartHome = readSmartHomeFromJSON();

        LightEventHandler lightHandler = new LightEventHandler(smartHome, new ConsoleNotifier());
        lightHandler.processEvent(new SensorEvent(SensorEventType.LIGHT_ON, "7"));
        lightHandler.processEvent(new SensorEvent(SensorEventType.LIGHT_ON, "8"));
        lightHandler.processEvent(new SensorEvent(SensorEventType.LIGHT_ON, "9"));

        HallDoorEventEventHandler handler = new HallDoorEventEventHandler(smartHome, new ConsoleCommandSender());
        handler.processEvent(new SensorEvent(SensorEventType.DOOR_CLOSED, "4"));

        smartHome.execute(c -> {
            if (c instanceof Door) {
                Door door = (Door) c;
                if (door.getId().equals("4")) {
                    assertFalse(door.isOpen());
                }
            }
        });

        smartHome.execute(c -> {
            if (c instanceof Light) {
                Light light = (Light) c;
                if (light.getId().equals("7") || light.getId().equals("8") || light.getId().equals("9")) {
                    assertFalse(light.isOn());
                }
            }
        });

    }
}