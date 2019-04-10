package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.homeelement.Light;
import ru.sbt.mipt.oop.remotecontrol.RemoteControlCommand;

public class TurnOnAllLightsCommand implements RemoteControlCommand {

    private final SmartHome smartHome;
    private final String rcId;

    public TurnOnAllLightsCommand(SmartHome smartHome, String rcId) {
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
            light.setOn(true);
            System.out.println("Light " + light.getId() + " was turned on.");
        });
    }

    @Override
    public String getID() {
        return rcId;
    }
}