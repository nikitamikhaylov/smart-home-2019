package ru.sbt.mipt.oop.Alarm;

public class Alarm {
    private boolean lightIsFlashing = false;
    private final String deactivationCode = "1488";
    private final String activationCode = "1488";
    private AlarmState currentState;

    public int getDeactivationCode() {
        return deactivationCode.hashCode();
    }

    public int getActivationCode() {
        return activationCode.hashCode();
    }

    public void changeState(AlarmState newState) {
        currentState = newState;
    }
}
