package ru.sbt.mipt.oop.Event;

import ru.sbt.mipt.oop.SmartHome;

public class EventHandlerDecorator implements EventHandler {
    private EventHandler handler;

    public EventHandlerDecorator(EventHandler handler) {
        this.handler = handler;
    }

    @Override
    public void fillEventList() {
        handler.fillEventList();
    }

    @Override
    public boolean isNotThisEventType(Event event) {
        return handler.isNotThisEventType(event);
    }

    @Override
    public void processEvent(SmartHome smartHome, Event event) {
        handler.processEvent(smartHome, event);
    }
}
