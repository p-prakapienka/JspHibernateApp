package by.prakapienka.at13java.web.view;

import by.prakapienka.at13java.model.User;
import by.prakapienka.at13java.service.UserService;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

@SpringView(name = EditUserView.VIEW_NAME)
public class EditUserView extends FormLayout implements View {

    public static final String VIEW_NAME = "editUserView";

    private UserService userService;

    private BeanItem<User> item;

    private TextField idField;
    private TextField nameField;
    private Button submitButton;

    @Autowired
    public EditUserView(UserService userService) {
        this.userService = userService;

        this.setSizeFull();

        this.idField = new TextField();
        this.idField.setVisible(false);

        this.nameField = new TextField("Name");

        this.submitButton = new Button("Save", this::onSubmit);

        this.addComponents(this.idField, this.nameField, this.submitButton);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        final Integer id = StringUtils.isEmpty(event.getParameters()) ? null : Integer.parseInt(event.getParameters());
        final User user = id != null ? userService.get(id) : new User();
        this.item = new BeanItem<>(user);
        this.idField.setPropertyDataSource(this.item.getItemProperty("id"));
        this.nameField.setPropertyDataSource(this.item.getItemProperty("name"));
    }

    private void onSubmit(Button.ClickEvent e) {
        final User user = this.item.getBean();
        if (user.isNew()) {
            userService.insert(user);
        } else {
            userService.update(user);
        }
        getUI().getNavigator().navigateTo(UsersView.VIEW_NAME);
    }
}
