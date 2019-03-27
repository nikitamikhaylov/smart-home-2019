package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.adapter.ССHandlerToNativeHandlerAdapter;
import ru.sbt.mipt.oop.alarm.AlarmEventHandler;
import ru.sbt.mipt.oop.homeelement.updatehomemodel.DoorEventHandler;
import ru.sbt.mipt.oop.homeelement.updatehomemodel.LightEventHandler;
import ru.sbt.mipt.oop.homereaderwriter.JsonSmartHomeReader;
import ru.sbt.mipt.oop.homereaderwriter.SmartHomeReader;
import ru.sbt.mipt.oop.notifications.ConsoleNotifier;

import java.io.IOException;

public class AdaptedApplication {
    public static void main(String[] args) throws IOException{
        SmartHome smartHome = readSmartHomeState();

        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(new ССHandlerToNativeHandlerAdapter(
                new DoorEventHandler(smartHome,  new ConsoleNotifier())));
        sensorEventsManager.registerEventHandler(new ССHandlerToNativeHandlerAdapter(
                new LightEventHandler(smartHome,  new ConsoleNotifier())));
        sensorEventsManager.registerEventHandler(new ССHandlerToNativeHandlerAdapter(
                new AlarmEventHandler(smartHome.getAlarm(), new ConsoleNotifier())));

        sensorEventsManager.start();
    }

    public static SmartHome readSmartHomeState() throws IOException {
        SmartHomeReader smartHomeReader = new JsonSmartHomeReader("smart-home-1.json");
        return smartHomeReader.readSmartHomeState();
    }

}