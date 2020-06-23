package com.karankumar.pomodoro.preferences;

/**
 * @author karan on 23/06/2020
 */
public class TimerPreferences {
    private int sessionDurationMinutes = 25;

    private int shortBreakDuration = 5;
    private int longBreakDuration = 15;
    private int pomodorosUntilLongBreak = 4;

    public int getSessionDurationMinutes() {
        return sessionDurationMinutes;
    }

    public int getShortBreakDuration() {
        return shortBreakDuration;
    }

    public int getLongBreakDuration() {
        return longBreakDuration;
    }

    public int getPomodorosUntilLongBreak() {
        return pomodorosUntilLongBreak;
    }
}
