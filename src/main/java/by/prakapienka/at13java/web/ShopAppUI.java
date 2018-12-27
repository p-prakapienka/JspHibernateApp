package by.prakapienka.at13java.web;

import by.prakapienka.at13java.web.view.ProductsView;
import by.prakapienka.at13java.web.view.StartView;
import by.prakapienka.at13java.web.view.UsersView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Theme("valo")
public class ShopAppUI extends UI {

    private ConfigurableApplicationContext applicationContext;

    private Navigator navigator;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        applicationContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");

        navigator = new Navigator(this, this);

        final StartView startView = applicationContext.getBean(StartView.class);
        startView.setNavigator(navigator);
        navigator.addView(StartView.VIEW_NAME, startView);

        final UsersView usersView = applicationContext.getBean(UsersView.class);
        usersView.setNavigator(navigator);
        navigator.addView(UsersView.VIEW_NAME, usersView);

        final ProductsView productsView = applicationContext.getBean(ProductsView.class);
        productsView.setNavigator(navigator);
        navigator.addView(ProductsView.VIEW_NAME, productsView);
    }

}
