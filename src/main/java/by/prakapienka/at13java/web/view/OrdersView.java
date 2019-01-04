package by.prakapienka.at13java.web.view;

import by.prakapienka.at13java.model.Order;
import by.prakapienka.at13java.service.OrderService;
import by.prakapienka.at13java.web.attribute.SessionAttribute;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.ClickableRenderer;
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
        this.grid.setSelectionMode(Grid.SelectionMode.NONE);
        this.grid.addItemClickListener(this::onRowClick);
        this.grid
                .getColumn("delete")
                .setRenderer(new ButtonRenderer(this::onDelete));
        this.grid
                .getColumn("edit")
                .setRenderer(new ButtonRenderer(this::onEdit));

        addComponent(grid);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        final int userId = Integer.parseInt(event.getParameters());
        getSession().setAttribute(SessionAttribute.USER_ID_ATTR, userId);
        this.orderService.getAll(userId).forEach(this.beanItemContainer::addBean);
    }

    private void onRowClick(ItemClickEvent e) {
        getUI().getNavigator().navigateTo(ItemsView.VIEW_NAME + "/"
                + this.beanItemContainer.getItem(e.getItemId()).getBean().getId());
    }

    private void onDelete(ClickableRenderer.RendererClickEvent e) {
        final Order order = this.beanItemContainer.getItem(e.getItemId()).getBean();
        this.orderService.delete(order.getId(),
                                 (Integer) getSession().getAttribute(SessionAttribute.USER_ID_ATTR));
        this.beanItemContainer.removeItem(e.getItemId());
    }

    private void onEdit(ClickableRenderer.RendererClickEvent e) {
        final Order order = this.beanItemContainer.getItem(e.getItemId()).getBean();
        getUI().getNavigator().navigateTo(EditOrderView.VIEW_NAME + "/" + order.getId());
    }

}
