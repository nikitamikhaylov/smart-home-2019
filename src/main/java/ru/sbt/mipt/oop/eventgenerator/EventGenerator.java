package ru.sbt.mipt.oop.eventgenerator;

import ru.sbt.mipt.oop.event.Event;

public interface EventGenerator {
    Event getNextSensorEvent();
}
