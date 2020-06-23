package com.karankumar.pomodoro;

import com.karankumar.pomodoro.preferences.TimerPreferences;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author karan on 23/06/2020
 */
public class CountdownTimer implements Runnable {
    private final TimerPreferences timerPreferences;

    private static final int MILLISECONDS_IN_SECOND = 1000;
    private static final int SECONDS_IN_MINUTE = 60;

    private AtomicInteger timeRemaining;

    private boolean pauseTimer;

    public CountdownTimer() {
        timerPreferences = new TimerPreferences();
    }

    @Override
    public void run() {
        int sessionLength = timerPreferences.getSessionDurationMinutes() * SECONDS_IN_MINUTE * MILLISECONDS_IN_SECOND;

        for(timeRemaining = new AtomicInteger(sessionLength); timeRemaining.get() > 0;
            timeRemaining.addAndGet(-MILLISECONDS_IN_SECOND)) {
            if (pauseTimer) {
                System.out.println("Stop timer");
                Thread.currentThread().interrupt();
            }
            try {
                System.out.println("Time remaining: " + timeRemaining.get());
                Thread.sleep(MILLISECONDS_IN_SECOND);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }
        }
        System.out.println("End of run()");
    }

    public void pauseTimer() {
        System.out.println("Stop timer called");
        pauseTimer = true;
    }

    public int getTimeRemaining() {
        return timeRemaining.get();
    }
}
