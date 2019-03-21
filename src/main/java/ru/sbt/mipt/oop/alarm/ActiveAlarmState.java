package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.notifications.ConsoleNotifier;

public class ActiveAlarmState extends AlarmState{

    public ActiveAlarmState(Alarm alarm) {
        super(alarm, new ConsoleNotifier());
    }

    @Override
    public void activate(String code) {
        notifier.notifyAbout("[WARNING] Duplicate activation");
    }

    @Override
    public void deactivate(String code) {
        if (alarm.verifyDeactivationCode(code)) {
            alarm.changeState(new InactiveAlarmState(alarm));
            notifier.notifyAbout("[INFO] alarm deactivated successfully");
        } else {
            notifier.notifyAbout("[ERROR] Wrong deactivation code");
        }
    }

    @Override
    public void switchToChaosMode() {
        alarm.changeState(new ChaosAlarmState(alarm));
        notifier.notifyAbout("[INFO] ALARM!!! CHAOS!!!");
    }
}
