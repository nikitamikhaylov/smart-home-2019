package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.notifications.ConsoleNotifier;
public class InactiveAlarmState extends AlarmState {

    public InactiveAlarmState(Alarm alarm) {
        super(alarm, new ConsoleNotifier());
    }

    @Override
    public void activate(String code) {
        if (alarm.verifyActivationCode(code)) {
            alarm.changeState(new ActiveAlarmState(alarm));
            notifier.notifyAbout("[INFO] alarm activated successfully");
        } else {
            notifier.notifyAbout("[ERROR] Wrong activation code");
        }
    }

    @Override
    public void deactivate(String code) {
        notifier.notifyAbout("[WARNING] Duplicate deactivation");
    }

    @Override
    public void switchToChaosMode() {
        notifier.notifyAbout("[WARNING] You must activate your alarm before switching to chaos mode");
    }
}
