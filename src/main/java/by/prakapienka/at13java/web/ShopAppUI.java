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

        navigator.addView(StartView.VIEW_NAME, applicationContext.getBean(StartView.class));
        navigator.addView(UsersView.VIEW_NAME, applicationContext.getBean(UsersView.class));
        navigator.addView(ProductsView.VIEW_NAME, applicationContext.getBean(ProductsView.class));
    }

}
