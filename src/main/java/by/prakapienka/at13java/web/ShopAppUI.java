package by.prakapienka.at13java.web;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
public class ShopAppUI extends UI {

    @Autowired
    private SpringNavigator navigator;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        navigator.init(this, this);
        this.setNavigator(navigator);
    }

}
