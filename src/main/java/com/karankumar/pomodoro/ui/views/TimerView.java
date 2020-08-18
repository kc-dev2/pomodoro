package com.karankumar.pomodoro.ui.views;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
public class TimerView extends VerticalLayout {
    private boolean isRunning = false;
    private Span timer;		// make instance variable to set time within createStateButton() method
    private CountdownTimer countdownTimer = new CountdownTimer();

    public TimerView() {
        setSizeFull();
        addClassName("timer");
        setAlignItems(Alignment.CENTER);

        this.timer = new Span("25:00");
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

//        CountdownTimer countdownTimer = new CountdownTimer();
//        ExecutorService e1 = Executors.newFixedThreadPool(1);
//        Future<Integer> timerCount = e1.submit(this.countdownTimer);
//        Thread thread = new Thread(countdownTimer);

        state.addClickListener(e -> {
            if (isRunning) {
                state.setIcon(new Icon(VaadinIcon.PLAY));
                countdownTimer.pauseTimer();
                System.out.println("thread interrupt timerview");
//                timerCount.interrupt();
            } else {
                state.setIcon(new Icon(VaadinIcon.STOP));
//                for(int i = 10; i > 0; i--) {
//                	updateTimer();
//                updateTimerNoThread();
                updateTimer();
//                timerCount.start();
//                timer.setText("something needs to go here");
            }
            isRunning = !isRunning;
        });
        return state;
    }

    private Span createTimer() {
        Span timer = new Span("10:00");
        timer.getElement().getStyle().set("color", "white");
        timer.getElement().getStyle().set("font-size", "70px");
        timer.getElement().setText("asdrfsdf");
        return timer;
    }
    
    private void updateTimer() {
    	ExecutorService e1 = Executors.newFixedThreadPool(1);
    	for(int i = 10; i > 0; i--) {
    		try {
    			System.out.println("loop");
    			Future<Integer> timerCount = e1.submit(this.countdownTimer);
    			String timerText = String.valueOf(timerCount.get());
    			System.out.println(timerText);
//        			this.timer.getElement().setText(timerText);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    }
    
    private void updateTimerNoThread()  {
    		try {
    			for(int j = 10; j > 0; j--) {
    	    		String foo = String.valueOf(j);
    	    		this.timer.setText(foo);
    	    		Thread.sleep(1000);
    			}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
}
