package ru.sbt.mipt.oop.Alarm;

import ru.sbt.mipt.oop.Event.Event;

public class AlarmEvent implements Event {
    private final AlarmEventType type;
    private final String code;

    public AlarmEvent(AlarmEventType type, String code) {
        this.type = type;
        this.code = code;
    }

    public AlarmEventType getType() {
        return type;
    }
    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "AlarmEvent{" +
                "type=" + type +
                ", code='" + code + '\'' +
                '}';
    }
}
