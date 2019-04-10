package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.homeelement.Light;

public class TurnOffHallLightsCommand implements RemoteControlCommand {

    private final SmartHome smartHome;
    private final String rcId;

    public TurnOffHallLightsCommand(SmartHome smartHome, String rcId) {
        this.smartHome = smartHome;
        this.rcId = rcId;
    }

    @Override
    public void execute() {
        smartHome.execute(obj -> {
            if (!(obj instanceof Light)) {
                return;
            }
            Light light = (Light) obj;
            light.setOn(false);
            System.out.println("Light " + light.getId() + " was turned off.");
        });
    }

    @Override
    public String getID() {
        return rcId;
    }
}
