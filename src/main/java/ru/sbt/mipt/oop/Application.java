package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.light.Light;
import ru.sbt.mipt.oop.light.LightEventHandler;
import ru.sbt.mipt.oop.notifications.ConsoleNotifier;
import ru.sbt.mipt.oop.reader.JsonSmartHomeReader;
import ru.sbt.mipt.oop.reader.SmartHomeReader;
import ru.sbt.mipt.oop.room.Room;
import ru.sbt.mipt.oop.room.DoorEventHandler;

import java.io.IOException;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    private static DoorEventHandler doorEventHandler = new DoorEventHandler(new ConsoleNotifier());
    private static LightEventHandler lightEventHandler = new LightEventHandler(new ConsoleNotifier());
    private static SmartHome smartHome = new SmartHome();

    public static void main(String... args) throws IOException {
        read();
        execute();
    }

    private static void read() throws IOException {
        SmartHomeReader smartHomeReader = new JsonSmartHomeReader("smart-home-1.json");
        smartHome = smartHomeReader.readSmartHomeState();
    }

    private static void execute() {
        // начинаем цикл обработки событий
        SensorEvent event = RandomEventGenerator.getNextSensorEvent();
        while (event != null) {
            System.out.println("[INFO] Got event: " + event);
            if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                lightEventHandler.processEvent(smartHome, event);
            }
            if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
                doorEventHandler.processEvent(smartHome, event);
            }
            event = RandomEventGenerator.getNextSensorEvent();
        }
    }
}
