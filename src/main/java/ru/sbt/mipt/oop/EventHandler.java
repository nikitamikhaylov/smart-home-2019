package ru.sbt.mipt.oop;

import java.util.ArrayList;

public interface EventHandler {
    final ArrayList<SensorEventType> eventsTypes = new ArrayList<>();
    public void fillEventList(); //must be private
    public boolean checkEventType(SensorEvent event); //must be private in Java9

    public void processEvent(SmartHome smartHome, SensorEvent event);
}
