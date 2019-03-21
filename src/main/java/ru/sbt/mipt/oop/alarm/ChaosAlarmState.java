package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.notifications.ConsoleNotifier;

public class ChaosAlarmState extends AlarmState {

    public ChaosAlarmState(Alarm alarm) {
        super(alarm, new ConsoleNotifier());
    }
    @Override
    public void activate(String code) {
        if (alarm.verifyActivationCode(code)) {
            alarm.changeState(new ActiveAlarmState(alarm));
            notifier.notifyAbout("[INFO] alarm activated successfully");
        } else {
            notifier.notifyAbout("[ERROR]Wrong activation code");
        }
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
        notifier.notifyAbout("[WARNING] System is already in ChaosMode");
    }
}
