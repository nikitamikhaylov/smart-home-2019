package ru.sbt.mipt.oop.Alarm;

import ru.sbt.mipt.oop.Event.Event;
import ru.sbt.mipt.oop.Event.EventHandler;
import ru.sbt.mipt.oop.Event.EventHandlerDecorator;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.notifications.ConsoleNotifier;
import ru.sbt.mipt.oop.notifications.Notifier;

public class SecurityHandlerDecorator extends EventHandlerDecorator {

    AlarmEventHandler alarmEventHandler;

    public SecurityHandlerDecorator(EventHandler handler) {
        super(handler);
        alarmEventHandler = new AlarmEventHandler(new ConsoleNotifier());
    }

    public SecurityHandlerDecorator(EventHandler handler, Notifier notifier) {
        super(handler);
        alarmEventHandler = new AlarmEventHandler(notifier);
    }

    @Override
    public void processEvent(SmartHome smartHome, Event event) {
        if (!super.isNotThisEventType(event)) {
            if (smartHome.getAlarm().getState().getClass().equals(ActiveAlarmState.class)) {
                smartHome.getAlarm().getState().swithToChaosMode();
                System.out.println("[Sending SMS] ALARM!!!");
                return;
            } else if (smartHome.getAlarm().getState().getClass().equals(ChaosAlarmState.class)) {
                System.out.println("[Sending SMS] ALARM!!!");
                return;
            } else {
                super.processEvent(smartHome, event);
                return;
            }
        }

        if (!alarmEventHandler.isNotThisEventType(event)) {
            alarmEventHandler.processEvent(smartHome, event);
        }
    }
}
