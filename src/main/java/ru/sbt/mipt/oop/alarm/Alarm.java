package ru.sbt.mipt.oop.alarm;

public class Alarm {
    private boolean lightIsFlashing = false;
    private final String deactivationCode;
    private final String activationCode;
    private AlarmState currentState = new InactiveAlarmState(this);

    public Alarm(String activationCode, String deactivationCode) {
        this.deactivationCode = deactivationCode;
        this.activationCode = activationCode;
    }

    public boolean verifyActivationCode(String code) {
        return activationCode.equals(code);
    }

    public boolean verifyDeactivationCode(String code) {
        return deactivationCode.equals(code);
    }

    public AlarmState getState() {
        return currentState;
    }

    public void activateWith(String code) {
        currentState.activate(code);
    }

    public void deactivateWith(String code) {
        currentState.deactivate(code);
    }

    public void switchToChaosMode() {
        currentState.switchToChaosMode();
    }

    void changeState(AlarmState newState) {
        currentState = newState;
    }
}
