package by.prakapienka.at13java.web.view;

import by.prakapienka.at13java.model.User;
import by.prakapienka.at13java.service.UserService;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.ClickableRenderer;
import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name = UsersView.VIEW_NAME)
public class UsersView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "usersView";

    private Grid grid;
    private GeneratedPropertyContainer propertyContainer;
    private BeanItemContainer<User> beanItemContainer;
    private Button createBtn;

    private UserService userService;

    @Autowired
    public UsersView(UserService userService) {
        this.userService = userService;

        this.setSizeFull();

        this.createBtn = new Button("Create", e -> {
            getUI().getNavigator().navigateTo(EditUserView.VIEW_NAME);
        });
        addComponent(createBtn);

        this.beanItemContainer = new BeanItemContainer<>(User.class);
        this.beanItemContainer.removeContainerProperty("orders");
        this.beanItemContainer.removeContainerProperty("new");

        this.propertyContainer = new GeneratedPropertyContainer(this.beanItemContainer);
        this.propertyContainer.addGeneratedProperty("edit", new PropertyValueGenerator<String>() {
            @Override
            public String getValue(Item item, Object itemId, Object propertyId) {
                return "Edit";
            }
            @Override
            public Class<String> getType() {
                return String.class;
            }
        });
        this.propertyContainer.addGeneratedProperty("delete", new PropertyValueGenerator<String>() {
            @Override
            public String getValue(Item item, Object itemId, Object propertyId) {
                return "Delete";
            }
            @Override
            public Class<String> getType() {
                return String.class;
            }
        });

        this.grid = new Grid(this.propertyContainer);
        this.grid
                .getColumn("delete")
                .setRenderer(new ButtonRenderer(this::onDelete));
        this.grid
                .getColumn("edit")
                .setRenderer(new ButtonRenderer(this::onEdit));
        addComponent(grid);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        fillData();
    }

    private void fillData() {
        beanItemContainer.removeAllItems();
        userService.getAll().forEach(beanItemContainer::addBean);
    }

    private void onDelete(ClickableRenderer.RendererClickEvent e) {
        final User user = this.beanItemContainer.getItem(e.getItemId()).getBean();
        this.userService.delete(user.getId());
        this.beanItemContainer.removeItem(e.getItemId());
    }

    private void onEdit(ClickableRenderer.RendererClickEvent e) {
        final User user = this.beanItemContainer.getItem(e.getItemId()).getBean();
        getUI().getNavigator().navigateTo(EditUserView.VIEW_NAME + "/" + user.getId());
    }

}
