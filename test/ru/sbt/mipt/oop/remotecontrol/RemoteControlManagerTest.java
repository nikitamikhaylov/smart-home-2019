package ru.sbt.mipt.oop.remotecontrol;

import org.junit.jupiter.api.Test;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.homeelement.Light;
import ru.sbt.mipt.oop.homereaderwriter.JsonSmartHomeReader;
import ru.sbt.mipt.oop.homereaderwriter.SmartHomeReader;

import java.io.IOException;
import java.rmi.server.ExportException;

import static org.junit.jupiter.api.Assertions.*;

class RemoteControlManagerTest {

    private SmartHome readSmartHomeFromJSON() throws IOException {
        SmartHomeReader smartHomeReader = new JsonSmartHomeReader("smart-home-1.json");
        return smartHomeReader.readSmartHomeState();
    }

    @Test
    void UnknownCommandTypeTest() throws Exception{
        SmartHome smartHome = readSmartHomeFromJSON();
        RemoteControlManager rcManager = new RemoteControlManager(smartHome);
        BasicRemoteControl remoteController = rcManager.createRController("123");

        assertThrows(RuntimeException.class, () -> {
            remoteController.onButtonPressed("F", "123");
        });

    }

    @Test
    void RemoteTurnOffLightTest() throws Exception {
        SmartHome smartHome = readSmartHomeFromJSON();
        RemoteControlManager rcManager = new RemoteControlManager(smartHome);
        BasicRemoteControl remoteController = rcManager.createRController("123");

        RemoteControlCommand command = rcManager.createCommand(RemoteControlCommandType.TURN_OFF_ALL_LIGHTS);
        remoteController.registryCommand("A", command);
        remoteController.onButtonPressed("A", "123");

        smartHome.execute(obj -> {
            if (!(obj instanceof Light)) {
                return;
            }
            Light light = (Light) obj;
            assertFalse(light.isOn());
        });
    }

    @Test
    void activateAlarmState() throws Exception{
        SmartHome smartHome = readSmartHomeFromJSON();
        RemoteControlManager rcManager = new RemoteControlManager(smartHome);
        BasicRemoteControl remoteController = rcManager.createRController("123");

        RemoteControlCommand command = rcManager.createCommand(RemoteControlCommandType.ENABLE_ALARM);
        remoteController.registryCommand("A", command);
    }
}