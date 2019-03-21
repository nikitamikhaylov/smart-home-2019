package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.event.EventType;

public enum AlarmEventType implements EventType {
    ALARM_ACTIVATE, ALARM_DEACTIVATE;

    public static int COUNT = AlarmEventType.values().length;
}
