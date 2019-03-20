package ru.sbt.mipt.oop.Event;

import ru.sbt.mipt.oop.Event.Event;
import ru.sbt.mipt.oop.SmartHome;

import java.util.ArrayList;

public interface EventHandler {
    public void fillEventList(); //must be private
    public boolean isNotThisEventType(Event event); //must be private in Java9

    public void processEvent(SmartHome smartHome, Event event);
}
