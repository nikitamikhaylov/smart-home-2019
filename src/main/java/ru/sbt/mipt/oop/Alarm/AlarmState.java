package ru.sbt.mipt.oop.Alarm;

import ru.sbt.mipt.oop.notifications.Notifier;

public abstract class AlarmState {
    Alarm alarm;
    Notifier notifier;

    AlarmState(Alarm alarm, Notifier notifier) {
        this.alarm = alarm;
        this.notifier = notifier;
    }

    public abstract void activate(String code);
    public abstract void deactivate(String code);
    public abstract void swithToChaosMode();
}
