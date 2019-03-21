package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.event.EventHandler;
import ru.sbt.mipt.oop.notifications.Notifier;

import static ru.sbt.mipt.oop.alarm.AlarmEventType.ALARM_ACTIVATE;

public class AlarmEventHandler implements EventHandler {
    private final Notifier notifier;
    private final Alarm alarm;

    public AlarmEventHandler(Alarm alarm, Notifier notifier) {
        this.notifier = notifier;
        this.alarm = alarm;
    }

    private boolean isAlarmEventType(Event anyEvent) {
        return anyEvent instanceof AlarmEvent;
    }

    public void processEvent(Event anyEvent) {
        if (!isAlarmEventType(anyEvent)) {
            return;
        }
        AlarmEvent event = (AlarmEvent) anyEvent;
        if (event.getType() == ALARM_ACTIVATE) {
            notifier.notifyAbout("[INFO] Attempt to activate alarm!");
            alarm.activateWith(event.getCode());
        } else {
            notifier.notifyAbout("[INFO] Attempt to deactivate alarm!");
            alarm.deactivateWith(event.getCode());
        }
    }
}
