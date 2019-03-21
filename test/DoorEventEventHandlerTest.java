import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.homeelement.Door;
import ru.sbt.mipt.oop.homeelement.updatehomemodel.DoorEventEventHandler;
import ru.sbt.mipt.oop.notifications.ConsoleNotifier;
import ru.sbt.mipt.oop.homereaderwriter.JsonSmartHomeReader;
import ru.sbt.mipt.oop.homereaderwriter.SmartHomeReader;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DoorEventEventHandlerTest {

    private SmartHome readSmartHomeFromJSON() throws IOException {
        SmartHomeReader smartHomeReader = new JsonSmartHomeReader("smart-home-1.json");
        return smartHomeReader.readSmartHomeState();
    }

    @Test
    public void testDoorClosedProcessEvent() throws IOException {
        SmartHome smartHome = readSmartHomeFromJSON();

        DoorEventEventHandler handler = new DoorEventEventHandler(smartHome, new ConsoleNotifier());
        handler.processEvent(new SensorEvent(SensorEventType.DOOR_CLOSED, "1"));

        smartHome.execute(c -> {
            if (c instanceof Door) {
                Door door = (Door) c;
                if (door.getId().equals("1")) {
                    assertTrue(door.isClosed());
                }
            }
        });
    }

    @Test
    public void testDoorOpenedProcessEvent() throws IOException {
        SmartHome smartHome = readSmartHomeFromJSON();

        DoorEventEventHandler handler = new DoorEventEventHandler(smartHome, new ConsoleNotifier());
        handler.processEvent(new SensorEvent(SensorEventType.DOOR_OPEN, "2"));

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