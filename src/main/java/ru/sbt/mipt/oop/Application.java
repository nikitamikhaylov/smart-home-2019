package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.command.ConsoleCommandSender;
import ru.sbt.mipt.oop.homeElement.door.HallDoorEventEventHandler;
import ru.sbt.mipt.oop.homeElement.door.DoorEventEventHandler;
import ru.sbt.mipt.oop.event.EventGenerator;
import ru.sbt.mipt.oop.event.RandomEventGenerator;
import ru.sbt.mipt.oop.homeElement.light.LightEventEventHandler;
import ru.sbt.mipt.oop.notifications.ConsoleNotifier;
import ru.sbt.mipt.oop.reader.JsonSmartHomeReader;
import ru.sbt.mipt.oop.reader.SmartHomeReader;
import ru.sbt.mipt.oop.sensor.SensorEvent;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;

public class Application {
    public static void main(String... args) throws IOException {
        Collection<EventHandler> eventHandlers = new ArrayList<EventHandler>();
        fillHandlersList(eventHandlers);
        SmartHome smartHome = readSmartHomeState();
        EventGenerator eventGenerator = new RandomEventGenerator();
        executeEventLoop(smartHome, eventGenerator, eventHandlers);
    }

    private static void fillHandlersList(Collection<EventHandler> eventHandlers) {
        eventHandlers.add(new DoorEventEventHandler(new ConsoleNotifier()));
        eventHandlers.add(new LightEventEventHandler(new ConsoleNotifier()));
        eventHandlers.add(new HallDoorEventEventHandler(new ConsoleCommandSender()));
    }

    public static SmartHome readSmartHomeState() throws IOException {
        SmartHomeReader smartHomeReader = new JsonSmartHomeReader("smart-home-1.json");
        return smartHomeReader.readSmartHomeState();
    }

    private static void executeEventLoop(SmartHome smartHome,
                                         EventGenerator eventGenerator,
                                         Collection<EventHandler> eventHandlers) {
        // начинаем цикл обработки событий
        SensorEvent event = eventGenerator.getNextSensorEvent();
        while (event != null) {
            System.out.println("[INFO] Got event: " + event);
            for (EventHandler eventHandler : eventHandlers) {
                eventHandler.processEvent(smartHome, event);
            }
            event = eventGenerator.getNextSensorEvent();
        }
    }
}
