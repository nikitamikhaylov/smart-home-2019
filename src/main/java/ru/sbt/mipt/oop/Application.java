package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.AlarmEventHandler;
import ru.sbt.mipt.oop.alarm.SecurityHandlerDecorator;
import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.event.EventHandler;
import ru.sbt.mipt.oop.command.ConsoleCommandSender;
import ru.sbt.mipt.oop.homeelement.scenarioexecutor.HallDoorEventEventHandler;
import ru.sbt.mipt.oop.homeelement.updatehomemodel.DoorEventHandler;
import ru.sbt.mipt.oop.eventgenerator.EventGenerator;
import ru.sbt.mipt.oop.eventgenerator.RandomEventGenerator;
import ru.sbt.mipt.oop.homeelement.updatehomemodel.LightEventHandler;
import ru.sbt.mipt.oop.notifications.ConsoleNotifier;
import ru.sbt.mipt.oop.homereaderwriter.JsonSmartHomeReader;
import ru.sbt.mipt.oop.homereaderwriter.SmartHomeReader;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;

public class Application {
    public static void main(String... args) throws IOException {
        Collection<EventHandler> eventHandlers = new ArrayList<EventHandler>();
        SmartHome smartHome = readSmartHomeState();
        fillHandlersList(smartHome, eventHandlers);
        EventGenerator eventGenerator = new RandomEventGenerator();
        executeEventLoop(eventGenerator, eventHandlers);
    }

    private static void fillHandlersList(SmartHome smartHome, Collection<EventHandler> eventHandlers) {
        eventHandlers.add(new SecurityHandlerDecorator(
                new DoorEventHandler(smartHome, new ConsoleNotifier()), smartHome.getAlarm())
        );
        eventHandlers.add(new SecurityHandlerDecorator(
                new LightEventHandler(smartHome, new ConsoleNotifier()), smartHome.getAlarm())
        );
        eventHandlers.add(new SecurityHandlerDecorator(
                new HallDoorEventEventHandler(smartHome, new ConsoleCommandSender()), smartHome.getAlarm())
        );
        eventHandlers.add(new AlarmEventHandler(smartHome.getAlarm(), new ConsoleNotifier()));
    }



    public static SmartHome readSmartHomeState() throws IOException {
        SmartHomeReader smartHomeReader = new JsonSmartHomeReader("smart-home-1.json");
        return smartHomeReader.readSmartHomeState();
    }

    private static void executeEventLoop(EventGenerator eventGenerator,
                                         Collection<EventHandler> eventHandlers) {
        // начинаем цикл обработки событий
        Event event = eventGenerator.getNextSensorEvent();
        while (event != null) {
            System.out.println("[INFO] Got event: " + event);
            for (EventHandler eventHandler : eventHandlers) {
                eventHandler.processEvent(event);
            }
            event = eventGenerator.getNextSensorEvent();
        }
    }
}
