package ru.sbt.mipt.oop.Alarm;

public class Alarm {
    private boolean lightIsFlashing = false;
    private final String deactivationCode;
    private final String activationCode;
    private AlarmState currentState = new DeactiveAlarmState(this);

    public Alarm(String deactivationCode, String activationCode) {
        this.deactivationCode = deactivationCode;
        this.activationCode = activationCode;
    }

    public int getDeactivationHashCode() {
        return deactivationCode.hashCode();
    }

    public int getActivationHashCode() {
        return activationCode.hashCode();
    }

    public void changeState(AlarmState newState) {
        currentState = newState;
    }

    public AlarmState getState() {
        return currentState;
    }
}
