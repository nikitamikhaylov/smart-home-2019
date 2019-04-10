package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.Alarm;

public class EnableSirenCommand implements RemoteControlCommand {

    private final Alarm alarm;
    private final String rcId;

    public EnableSirenCommand(Alarm alarm, String rcId) {
        this.alarm = alarm;
        this.rcId = rcId;
    }

    @Override
    public void execute() {
        alarm.switchToChaosMode();
    }

    @Override
    public String getID() {
        return rcId;
    }
}
