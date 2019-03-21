package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.event.EventHandler;
import ru.sbt.mipt.oop.sensor.SensorEvent;

public class SecurityHandlerDecorator implements EventHandler {

    private final EventHandler wrappedEventHandler;
    private final Alarm alarm;

    public SecurityHandlerDecorator(EventHandler wrappedEventHandler, Alarm alarm) {
        this.wrappedEventHandler = wrappedEventHandler;
        this.alarm = alarm;
    }

    private boolean isSensorEvent(Event event) {
        return event instanceof SensorEvent;
    }

    @Override
    public void processEvent(Event event) {
        if (!isSensorEvent(event)) {
            return;
        }
        if (alarm.getState() instanceof ActiveAlarmState) {
            alarm.switchToChaosMode();
            System.out.println("[Sending SMS] ALARM!!!");
            return;
        } else if (alarm.getState() instanceof ChaosAlarmState) {
            System.out.println("[Sending SMS] ALARM!!!");
            return;
        }
        wrappedEventHandler.processEvent(event);
    }
}
