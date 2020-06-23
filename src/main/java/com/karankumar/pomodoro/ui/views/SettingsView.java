package com.karankumar.pomodoro.ui.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "settings", layout = MainView.class)
@PageTitle("Timer")
public class SettingsView extends VerticalLayout {
    public SettingsView() {
    }
}
