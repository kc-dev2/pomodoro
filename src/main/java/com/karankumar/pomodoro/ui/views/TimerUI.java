package com.karankumar.pomodoro.ui.views;

import com.karankumar.pomodoro.CountdownTimer;
import com.vaadin.flow.component.html.Span;

/**
 * Updates the user interface elements of the timer
 */
public class TimerUI implements Runnable {

	private Span timer;
	private CountdownTimer countdownTimer;
	private final Thread timerThread;

	public TimerUI() {
		countdownTimer = new CountdownTimer();
		timerThread = new Thread(countdownTimer);
//		timerThread.start();
	}

	public Span createTimer() {
		timer = new Span("00:00");
		timer.getElement().getStyle().set("color", "white");
		timer.getElement().getStyle().set("font-size", "70px");
		return timer;
	}

	public void runTimer() {
		timerThread.start();
	}

	@Override
	public void run() {
		if (countdownTimer != null) {
			System.out.println("Update timer");
			timer.setText("" + countdownTimer.getTimeRemaining());
		}
	}

	public void pause() {
		countdownTimer.pauseTimer();
		timerThread.interrupt();
	}
}
