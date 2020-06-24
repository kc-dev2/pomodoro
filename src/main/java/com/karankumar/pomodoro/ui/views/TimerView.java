package com.karankumar.pomodoro.ui.views;

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
//public class TimerView extends VerticalLayout implements Runnable {
public class TimerView extends VerticalLayout {
    private volatile boolean isRunning = false;
    private final TimerUI timerUI;

    public TimerView() {
        setSizeFull();
        addClassName("timer");
        setAlignItems(Alignment.CENTER);

        timerUI = new TimerUI();
        Span timer = timerUI.createTimer();
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

        state.addClickListener(e -> {
            if (isRunning) {
                state.setIcon(new Icon(VaadinIcon.PLAY));
                timerUI.pause();
            } else {
                state.setIcon(new Icon(VaadinIcon.PAUSE));
				timerUI.runTimer();
				timerUI.run();
            }
            isRunning = !isRunning;
        });
        return state;
    }
}
