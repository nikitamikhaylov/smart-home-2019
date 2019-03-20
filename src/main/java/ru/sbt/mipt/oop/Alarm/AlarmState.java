package ru.sbt.mipt.oop.Alarm;

public abstract class AlarmState {
    Alarm alarm;

    AlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    public abstract void activate(String code);
    public abstract void deactivate(String code);
    public abstract void swithToChaosMode();
}
