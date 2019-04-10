package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.homeelement.Door;
import ru.sbt.mipt.oop.homeelement.Light;
import ru.sbt.mipt.oop.homeelement.Room;

public class CloseHallDoorCommand implements RemoteControlCommand {

    private final SmartHome smartHome;
    private final String rcId;

    public CloseHallDoorCommand(SmartHome smartHome, String rcId) {
        this.smartHome = smartHome;
        this.rcId = rcId;
    }

    @Override
    public void execute() {
        smartHome.execute(o -> {
                    if (o instanceof Room) {
                        Room room = (Room) o;
                        if (room.getName().equals("hall")) {
                            room.execute(o1 -> {
                                if (o1 instanceof Door) {
                                    Door door = (Door) o1;
                                    door.setOpen(false);
                                }
                            });
                            smartHome.execute(o2 -> {
                                if (o2 instanceof Light) {
                                    Light light = (Light) o2;
                                    light.setOn(false);
                                }
                            });
                        }
                    }
                }
        );
    }

    @Override
    public String getID() {
        return rcId;
    }
}
