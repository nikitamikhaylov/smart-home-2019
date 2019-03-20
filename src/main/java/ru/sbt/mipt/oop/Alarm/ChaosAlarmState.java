package ru.sbt.mipt.oop.Alarm;

public class ChaosAlarmState extends AlarmState {

    public ChaosAlarmState(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void activate(String code) {
        if (alarm.getActivationCode()== code.hashCode()) {
            alarm.changeState(new ActiveAlarmState(alarm));
        } else {
            System.out.println("Wrong activation code");
        }
    }

    @Override
    public void deactivate(String code) {
        if (alarm.getDeactivationCode() == code.hashCode()) {
            alarm.changeState(new DeactiveAlarmState(alarm));
        } else {
            System.out.println("Wrong deactivation code");
        }
    }

    @Override
    public void swithToChaosMode() {
        System.out.println("[WARNING] System is already in ChaosMode");
    }
}
