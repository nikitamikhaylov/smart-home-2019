package ru.sbt.mipt.oop.Alarm;

import ru.sbt.mipt.oop.notifications.ConsoleNotifier;
import ru.sbt.mipt.oop.notifications.Notifier;

public class ActiveAlarmState extends AlarmState{

    public ActiveAlarmState(Alarm alarm) {
        super(alarm, new ConsoleNotifier());
    }

    public ActiveAlarmState(Alarm alarm, Notifier notifier) {
        super(alarm, notifier);
    }

    @Override
    public void activate(String code) {
        notifier.notifyAbout("[WARNING] Duplicate activation");
    }

    @Override
    public void deactivate(String code) {
        if (alarm.getDeactivationHashCode() == code.hashCode()) {
            alarm.changeState(new DeactiveAlarmState(alarm));
            notifier.notifyAbout("[INFO] Alarm deactivated successfully");
        } else {
            notifier.notifyAbout("[ERROR] Wrong deactivation code");
        }
    }

    @Override
    public void swithToChaosMode() {
        alarm.changeState(new ChaosAlarmState(alarm));
        notifier.notifyAbout("[INFO] ALARM!!! CHAOS!!!");
    }
}
