package ru.sbt.mipt.oop.Alarm;

import ru.sbt.mipt.oop.Event.EventType;

public enum AlarmEventType implements EventType {
    ALARM_ACTIVATE, ALARM_DEACTIVATE;

    public static int COUNT = AlarmEventType.values().length;
}
