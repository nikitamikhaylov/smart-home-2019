package ru.sbt.mipt.oop.Alarm;

import ru.sbt.mipt.oop.notifications.ConsoleNotifier;
import ru.sbt.mipt.oop.notifications.Notifier;

public class ChaosAlarmState extends AlarmState {

    public ChaosAlarmState(Alarm alarm) {
        super(alarm, new ConsoleNotifier());
    }

    public ChaosAlarmState(Alarm alarm, Notifier notifier) {
        super(alarm, notifier);
    }

    @Override
    public void activate(String code) {
        if (alarm.getActivationHashCode()== code.hashCode()) {
            alarm.changeState(new ActiveAlarmState(alarm));
            notifier.notifyAbout("[INFO] Alarm activated successfully");
        } else {
            notifier.notifyAbout("[ERROR]Wrong activation code");
        }
    }

    @Override
    public void deactivate(String code) {
        if (alarm.getDeactivationHashCode() == code.hashCode()) {
            alarm.changeState(new DeactiveAlarmState(alarm));
            notifier.notifyAbout("[INFO] Alarm deactivated successfully");
        } else {
            notifier.notifyAbout("[ERROR] Wrong deactivation code");
        }
    }

    @Override
    public void swithToChaosMode() {
        notifier.notifyAbout("[WARNING] System is already in ChaosMode");
    }
}
