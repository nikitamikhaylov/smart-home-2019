package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.SmartHome;

public class RemoteControlManager {
    private final SmartHome smartHome;
    private String RCID = "-1";

    public RemoteControlManager(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public BasicRemoteControl createRController(String RCID) {
        System.out.println("Created controller with ID: " + RCID);
        this.RCID = RCID;
        return new BasicRemoteControl(RCID, smartHome);
    }

    public RemoteControlCommand createCommand(RemoteControlCommandType commandType) {
        switch (commandType) {
            case CLOSE_HALL_DOOR:
                return new CloseHallDoorCommand(smartHome, RCID);
            case ENABLE_ALARM:
                return new EnableAlarmCommand(smartHome.getAlarm(), RCID);
            case ENABLE_SIREN:
                return new EnableSirenCommand(smartHome.getAlarm(), RCID);
            case TURN_OFF_ALL_LIGHTS:
                return new TurnOffAllLightsCommand(smartHome, RCID);
            case TURN_OFF_HALL_LIGHT:
                return new TurnOffHallLightsCommand(smartHome, RCID);
            case TURN_ON_ALL_LIGHTS:
                return new TurnOnAllLightsCommand(smartHome, RCID);
            default:
                throw new RuntimeException("Unknown command type");
        }
    }
}
