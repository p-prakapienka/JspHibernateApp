package by.prakapienka.at13java.web.view;

import by.prakapienka.at13java.service.UserService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UsersView extends VerticalLayout implements View {

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

    private void fillData() {
        userService.getAll().forEach(u -> grid.addRow(u.getId(), u.getName()));
    }

}
