package ru.sbt.mipt.oop.Alarm;

public class DeactiveAlarmState extends AlarmState {

    public DeactiveAlarmState(Alarm alarm) {
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
        System.out.println("[WARNING] Duplicate deactivation");
    }

    @Override
    public void swithToChaosMode() {
        alarm.changeState(new ChaosAlarmState(alarm));
    }
}
