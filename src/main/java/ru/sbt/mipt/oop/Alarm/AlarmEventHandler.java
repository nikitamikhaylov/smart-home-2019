package ru.sbt.mipt.oop.Alarm;

import ru.sbt.mipt.oop.Event.Event;
import ru.sbt.mipt.oop.Event.EventHandler;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.notifications.Notifier;

import java.util.ArrayList;
import java.util.List;

import static ru.sbt.mipt.oop.Alarm.AlarmEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.Alarm.AlarmEventType.ALARM_DEACTIVATE;

public class AlarmEventHandler implements EventHandler {
    private final Notifier notifier;
    private final List<AlarmEventType> eventsTypes = new ArrayList<>();

    public AlarmEventHandler(Notifier notifier) {
        fillEventList();
        this.notifier = notifier;
    }

    @Override
    public void fillEventList() {
        eventsTypes.add(ALARM_ACTIVATE);
        eventsTypes.add(ALARM_DEACTIVATE);
    }

    @Override
    public boolean isNotThisEventType(Event anyEvent) {
        if (!(anyEvent instanceof AlarmEvent)) {
            return true;
        }
        AlarmEvent event = (AlarmEvent) anyEvent;
        return !eventsTypes.contains(event.getType());
    }

    @Override
    public void processEvent(SmartHome smartHome, Event anyEvent) {
        if (isNotThisEventType(anyEvent)) {
            return;
        }
        AlarmEvent event = (AlarmEvent) anyEvent;
        if (event.getType() == ALARM_ACTIVATE) {
            notifier.notifyAbout("[INFO] Attempt to activate alarm!");
            smartHome.getAlarm().getState().activate(event.getCode());
        } else {
            notifier.notifyAbout("[INFO] Attempt to deactivate alarm!");
            smartHome.getAlarm().getState().deactivate(event.getCode());
        }
    }
}
