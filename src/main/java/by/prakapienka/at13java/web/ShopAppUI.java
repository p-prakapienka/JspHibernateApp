package by.prakapienka.at13java.web;

import by.prakapienka.at13java.service.UserService;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ShopAppUI extends UI {

    private ConfigurableApplicationContext applicationContext;

    private UserService userService;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        applicationContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        userService = applicationContext.getBean(UserService.class);

        final VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        setContent(content);

        final Grid grid = new Grid();
        grid.addColumn("id", Integer.class);
        grid.addColumn("name", String.class);
        fillUsers(grid);
        content.addComponent(grid);
    }

    private void fillUsers(final Grid grid) {
        userService.getAll().forEach(u -> grid.addRow(u.getId(), u.getName()));
    }

}
