package ru.sbt.mipt.oop.adapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.sensor.SensorEvent;

public class ССHandlerToNativeHandlerAdapter implements EventHandler {

    ru.sbt.mipt.oop.event.EventHandler eventHandler;

    public ССHandlerToNativeHandlerAdapter(ru.sbt.mipt.oop.event.EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        try {
            eventHandler.processEvent(new CCSensorEventToNativeSensorEventAdapter(event));
        } catch (RuntimeException exception) {
            System.out.println(event.getEventType() + " is not supported");
        }
    }
}
