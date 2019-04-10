package ru.sbt.mipt.oop.remotecontrol;

import rc.RemoteControl;
import ru.sbt.mipt.oop.SmartHome;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BasicRemoteControl implements RemoteControl {

    private String rcId;
    private SmartHome smartHome;
    private HashMap<String, RemoteControlCommand> codeToCommand = new HashMap<>();
    private final List<String> buttons = Arrays.asList("1", "2", "3", "4", "A", "B", "C", "D");

    public BasicRemoteControl(String rcId, SmartHome smartHome) {
        this.rcId = rcId;
        this.smartHome = smartHome;
    }

    public void registryCommand(String buttonCode, RemoteControlCommand command) {
        if (buttons.contains(buttonCode)) {
            codeToCommand.put(buttonCode, command);
        }
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (!codeToCommand.containsKey(buttonCode)) {
            throw new RuntimeException("Unknown command for this remote control instance");
        }
        codeToCommand.get(buttonCode).execute();
    }
}
