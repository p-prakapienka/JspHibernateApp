package by.prakapienka.at13java.web;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.UI;

@SpringUI
@SpringViewDisplay
@Theme("valo")
public class ShopAppUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    }

}
