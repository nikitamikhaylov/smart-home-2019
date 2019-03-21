import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.homeelement.Light;
import ru.sbt.mipt.oop.homeelement.updatehomemodel.LightEventEventHandler;
import ru.sbt.mipt.oop.notifications.ConsoleNotifier;
import ru.sbt.mipt.oop.homereaderwriter.JsonSmartHomeReader;
import ru.sbt.mipt.oop.homereaderwriter.SmartHomeReader;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LightEventEventHandlerTest {
    private SmartHome readSmartHomeFromJSON() throws IOException {
        SmartHomeReader smartHomeReader = new JsonSmartHomeReader("smart-home-1.json");
        return smartHomeReader.readSmartHomeState();
    }

    @Test
    public void testLightOnProcessEvent() throws IOException {
        SmartHome smartHome = readSmartHomeFromJSON();

        LightEventEventHandler handler = new LightEventEventHandler(smartHome, new ConsoleNotifier());
        handler.processEvent(new SensorEvent(SensorEventType.LIGHT_ON, "7"));

        smartHome.execute(c -> {
            if (c instanceof Light) {
                Light light = (Light) c;
                if (light.getId().equals("7")) {
                    assertTrue(light.isOn());
                }
            }
        });
    }

    @Test
    public void testLightOffProcessEvent() throws IOException {
        SmartHome smartHome = readSmartHomeFromJSON();

        LightEventEventHandler handler = new LightEventEventHandler(smartHome, new ConsoleNotifier());
        handler.processEvent(new SensorEvent(SensorEventType.LIGHT_OFF, "2"));

        smartHome.execute(c -> {
            if (c instanceof Light) {
                Light light = (Light) c;
                if (light.getId().equals("2")) {
                    assertFalse(light.isOn());
                }
            }
        });

    }
}