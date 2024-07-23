package com.victorfelipejr.stocks_tracker.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route("")
@PageTitle("Stocks Tracker")
public class MainView extends VerticalLayout {

    public MainView() {
        Button button = new Button("Click me", e -> Notification.show("Hello, Spring+Vaadin user!"));
        add(button);
    }
}
