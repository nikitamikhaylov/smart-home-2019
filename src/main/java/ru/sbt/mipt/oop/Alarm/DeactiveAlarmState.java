package ru.sbt.mipt.oop.Alarm;

import ru.sbt.mipt.oop.notifications.ConsoleNotifier;
import ru.sbt.mipt.oop.notifications.Notifier;

public class DeactiveAlarmState extends AlarmState {

    public DeactiveAlarmState(Alarm alarm) {
        super(alarm, new ConsoleNotifier());
    }

    public DeactiveAlarmState(Alarm alarm, Notifier notifier) {
        super(alarm, notifier);
    }

    @Override
    public void activate(String code) {
        if (alarm.getActivationHashCode()== code.hashCode()) {
            alarm.changeState(new ActiveAlarmState(alarm));
            notifier.notifyAbout("[INFO] Alarm activated successfully");
        } else {
            notifier.notifyAbout("[ERROR] Wrong activation code");
        }
    }

    @Override
    public void deactivate(String code) {
        notifier.notifyAbout("[WARNING] Duplicate deactivation");
    }

    @Override
    public void swithToChaosMode() {
        notifier.notifyAbout("[WARNING] You must activate your alarm before switching to chaos mode");
    }
}
