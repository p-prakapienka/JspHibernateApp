package by.prakapienka.at13java.web.view;

import by.prakapienka.at13java.model.Order;
import by.prakapienka.at13java.service.OrderService;
import by.prakapienka.at13java.web.attribute.SessionAttribute;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name = OrdersView.VIEW_NAME)
public class OrdersView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "ordersView";

    private Grid grid;
    private GeneratedPropertyContainer propertyContainer;
    private BeanItemContainer<Order> beanItemContainer;
    private Button createBtn;

    private OrderService orderService;

    @Autowired
    public OrdersView(OrderService orderService) {
        this.orderService = orderService;

        this.createBtn = new Button("Create", e -> {
            getUI().getNavigator()
                   .navigateTo(EditOrderView.VIEW_NAME);
        });
        addComponent(this.createBtn);

        this.beanItemContainer = new BeanItemContainer<>(Order.class);
        this.beanItemContainer.removeContainerProperty("new");
        this.beanItemContainer.removeContainerProperty("orderItems");
        this.beanItemContainer.removeContainerProperty("user");

        this.propertyContainer = new GeneratedPropertyContainer(this.beanItemContainer);
        //TODO buttons

        this.grid = new Grid(this.propertyContainer);
        this.grid.setSelectionMode(Grid.SelectionMode.NONE);

        addComponent(grid);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        final int userId = Integer.parseInt(event.getParameters());
        getSession().setAttribute(SessionAttribute.USER_ID_ATTR, userId);
        this.orderService.getAll(userId).forEach(this.beanItemContainer::addBean);
    }

}
