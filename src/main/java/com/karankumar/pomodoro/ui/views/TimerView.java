package com.karankumar.pomodoro.ui.views;

import com.karankumar.pomodoro.CountdownTimer;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "timer", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Timer")
public class TimerView extends VerticalLayout implements Runnable {
    private volatile boolean isRunning = false;
    private Span timer;
    private CountdownTimer countdownTimer;

    public TimerView() {
        setSizeFull();
        addClassName("timer");
        setAlignItems(Alignment.CENTER);

        Span timer = createTimer();
        HorizontalLayout horizontalLayout = new HorizontalLayout(timer);
        horizontalLayout.setSizeFull();
        horizontalLayout.setAlignItems(Alignment.CENTER);
        horizontalLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        add(horizontalLayout);

        Button start = createStateButton();
        HorizontalLayout horizontalLayout2 = new HorizontalLayout(start);
        horizontalLayout2.setSizeFull();
        horizontalLayout2.setAlignItems(Alignment.CENTER);
        horizontalLayout2.setJustifyContentMode(JustifyContentMode.CENTER);
        add(horizontalLayout2);
    }

    private Button createStateButton() {
        Button state = new Button();
        state.setIcon(new Icon(VaadinIcon.PLAY)); // initial state
        state.setMinHeight("50px");
        state.setMinWidth("50px");

        countdownTimer = new CountdownTimer();
        Thread thread = new Thread(countdownTimer);

        state.addClickListener(e -> {
            if (isRunning) {
                state.setIcon(new Icon(VaadinIcon.PLAY));
                countdownTimer.pauseTimer();
                thread.interrupt();
            } else {
                state.setIcon(new Icon(VaadinIcon.PAUSE));
                thread.start();
            }
            isRunning = !isRunning;
        });
        return state;
    }

    private Span createTimer() {
        timer = new Span("00:00");
        timer.getElement().getStyle().set("color", "white");
        timer.getElement().getStyle().set("font-size", "70px");
        return timer;
    }

    private void updateTimer() {
        Thread uiThread = new Thread(this);
        uiThread.start();
        while (isRunning) {
            ; // don't exit method yet
        }
        Thread.currentThread().interrupt();
    }

    @Override
    public void run() {
        if (countdownTimer != null) {
            timer.setText("" + countdownTimer.getTimeRemaining());
        }
    }
}
