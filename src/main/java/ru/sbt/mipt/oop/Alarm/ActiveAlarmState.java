package ru.sbt.mipt.oop.Alarm;

public class ActiveAlarmState extends AlarmState{

    public ActiveAlarmState(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void activate(String code) {
        System.out.println("[WARNING] Duplicate activation");
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
        alarm.changeState(new ChaosAlarmState(alarm));
    }
}
