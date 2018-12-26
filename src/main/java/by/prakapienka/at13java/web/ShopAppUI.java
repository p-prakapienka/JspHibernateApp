package by.prakapienka.at13java.web;

import by.prakapienka.at13java.web.view.UsersView;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ShopAppUI extends UI {

    private ConfigurableApplicationContext applicationContext;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        applicationContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");

        final VerticalLayout content = applicationContext.getBean(UsersView.class);
        ((UsersView) content).enter(null);
        setContent(content);
    }

}
