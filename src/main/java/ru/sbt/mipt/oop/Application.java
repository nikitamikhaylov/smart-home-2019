package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.light.LightEventHandler;
import ru.sbt.mipt.oop.notifications.ConsoleNotifier;
import ru.sbt.mipt.oop.reader.JsonSmartHomeReader;
import ru.sbt.mipt.oop.reader.SmartHomeReader;
import ru.sbt.mipt.oop.room.DoorEventHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {
    private static Collection<Handler> handlers = new ArrayList<Handler>();
    private static SmartHome smartHome = new SmartHome();

    private static void fillHandlersList() {
        handlers.add(new DoorEventHandler(new ConsoleNotifier()));
        handlers.add(new LightEventHandler(new ConsoleNotifier()));
    }

    public static void main(String... args) throws IOException {
        read();
        executeRandomEventLoop();
    }

    private static void read() throws IOException {
        SmartHomeReader smartHomeReader = new JsonSmartHomeReader("smart-home-1.json");
        smartHome = smartHomeReader.readSmartHomeState();
    }

    private static void executeRandomEventLoop() {
        fillHandlersList();
        // начинаем цикл обработки событий
        SensorEvent event = RandomEventGenerator.getNextSensorEvent();
        while (event != null) {
            System.out.println("[INFO] Got event: " + event);
            for (Handler handler: handlers) {
                handler.processEvent(smartHome, event);
            }
            event = RandomEventGenerator.getNextSensorEvent();
        }
    }
}
