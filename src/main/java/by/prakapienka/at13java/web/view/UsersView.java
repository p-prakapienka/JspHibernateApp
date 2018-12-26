package by.prakapienka.at13java.web.view;

import by.prakapienka.at13java.service.UserService;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UsersView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "usersView";

    private Navigator navigator;

    private Grid grid;

    private UserService userService;

    @Autowired
    public UsersView(UserService userService) {
        this.userService = userService;

        this.setSizeFull();

        this.grid = new Grid();
        grid.addColumn("id", Integer.class);
        grid.addColumn("name", String.class);
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
        grid.getContainerDataSource().removeAllItems();
        userService.getAll().forEach(u -> grid.addRow(u.getId(), u.getName()));
    }

}