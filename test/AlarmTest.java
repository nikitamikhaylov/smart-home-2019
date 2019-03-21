import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.alarm.ActiveAlarmState;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.ChaosAlarmState;
import ru.sbt.mipt.oop.alarm.InactiveAlarmState;

import static org.junit.jupiter.api.Assertions.*;

class AlarmTest {

    @Test
    void verifyActivationCode() {
        Alarm alarm = new Alarm("1488", "1488");
        assertTrue(alarm.verifyActivationCode("1488"));
        assertFalse(alarm.verifyActivationCode("1"));
    }

    @Test
    void verifyDeactivationCode() {
        Alarm alarm = new Alarm("1488", "1488");
        assertTrue(alarm.verifyDeactivationCode("1488"));
        assertFalse(alarm.verifyDeactivationCode("1"));
    }

    @Test
    void activateDeactivateWith() {
        Alarm alarm = new Alarm("1488", "1488");
        assertTrue(alarm.getState() instanceof InactiveAlarmState);
        alarm.activateWith("1488");
        assertTrue(alarm.getState() instanceof ActiveAlarmState);
        alarm.deactivateWith("1");
        assertTrue(alarm.getState() instanceof ActiveAlarmState);
        alarm.deactivateWith("1488");
        assertTrue(alarm.getState() instanceof InactiveAlarmState);
    }

    @Test
    void deactivateFromAlarmState() {
        Alarm alarm = new Alarm("1488", "1488");
        assertTrue(alarm.getState() instanceof InactiveAlarmState);
        alarm.activateWith("1488");
        alarm.switchToChaosMode();
        assertTrue(alarm.getState() instanceof ChaosAlarmState);
        alarm.deactivateWith("1");
        assertTrue(alarm.getState() instanceof ChaosAlarmState);
        alarm.deactivateWith("1488");
        assertTrue(alarm.getState() instanceof InactiveAlarmState);
    }

    @Test
    void activateFromAlarmState() {
        Alarm alarm = new Alarm("1488", "1488");
        assertTrue(alarm.getState() instanceof InactiveAlarmState);
        alarm.activateWith("1488");
        alarm.switchToChaosMode();
        assertTrue(alarm.getState() instanceof ChaosAlarmState);
        alarm.activateWith("1");
        assertTrue(alarm.getState() instanceof ChaosAlarmState);
        alarm.activateWith("1488");
        assertTrue(alarm.getState() instanceof ActiveAlarmState);
    }

    @Test
    void switchToChaosStateFromInactiveState() {
        Alarm alarm = new Alarm("1488", "1488");
        assertTrue(alarm.getState() instanceof InactiveAlarmState);
        alarm.switchToChaosMode();
        assertTrue(alarm.getState() instanceof InactiveAlarmState);
    }
}