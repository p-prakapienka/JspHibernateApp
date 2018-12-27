package by.prakapienka.at13java.web.view;

import by.prakapienka.at13java.model.User;
import by.prakapienka.at13java.service.UserService;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UsersView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "usersView";

    private Navigator navigator;
    private Grid grid;
    private BeanItemContainer<User> beanItemContainer;
    private Button createBtn;

    private UserService userService;

    @Autowired
    public UsersView(UserService userService) {
        this.userService = userService;

        this.setSizeFull();

        this.createBtn = new Button("Create", e -> {
        });
        addComponent(createBtn);

        this.beanItemContainer = new BeanItemContainer<>(User.class);
        this.beanItemContainer.removeContainerProperty("orders");
        this.beanItemContainer.removeContainerProperty("new");

        this.grid = new Grid(this.beanItemContainer);
        addComponent(grid);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        fillData();
    }

    public void setNavigator(final Navigator navigator) {
        this.navigator = navigator;
    }

    private void fillData() {
        beanItemContainer.removeAllItems();
        userService.getAll().forEach(beanItemContainer::addBean);
    }

}
