package ru.sbt.mipt.oop.EventGenerator;

import ru.sbt.mipt.oop.Event.Event;

public interface EventGenerator {
    Event getNextSensorEvent();
}
