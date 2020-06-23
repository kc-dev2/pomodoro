package com.karankumar.pomodoro.ui.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "timer", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Timer")
public class TimerView extends VerticalLayout {
    public TimerView() {
        setSizeFull();
        addClassName("timer");
    }
}
