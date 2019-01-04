package by.prakapienka.at13java.web.view;

import by.prakapienka.at13java.model.Order;
import by.prakapienka.at13java.model.OrderItem;
import by.prakapienka.at13java.service.OrderService;
import by.prakapienka.at13java.web.attribute.SessionAttribute;
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

@SpringView(name = ItemsView.VIEW_NAME)
public class ItemsView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "itemsView";

    private OrderService orderService;

    private BeanItemContainer<OrderItem> beanItemContainer;
    private GeneratedPropertyContainer propertyContainer;
    private Grid grid;
    private Button addButton;

    private Order order;

    @Autowired
    public ItemsView(OrderService orderService) {
        this.orderService = orderService;

        this.beanItemContainer = new BeanItemContainer<>(OrderItem.class);
        this.beanItemContainer.removeContainerProperty("new");

        this.propertyContainer = new GeneratedPropertyContainer(this.beanItemContainer);
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

        this.addButton = new Button("Add", e -> {
            getUI().getNavigator().navigateTo(AddItemView.VIEW_NAME + "/" + order.getId());
        });

        this.grid = new Grid(this.propertyContainer);
        this.grid
                .getColumn("delete")
                .setRenderer(new ButtonRenderer(this::onDelete));

        addComponents(this.addButton, this.grid);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        final Integer userId = (Integer) getSession().getAttribute(SessionAttribute.USER_ID_ATTR);
        final int orderId = Integer.parseInt(event.getParameters());
        this.order = orderService.getWithItems(orderId, userId);
        this.beanItemContainer.removeAllItems();
        this.beanItemContainer.addAll(order.getOrderItems());
    }

    private void onDelete(ClickableRenderer.RendererClickEvent e) {
        final Integer userId = (Integer) getSession().getAttribute(SessionAttribute.USER_ID_ATTR);
        final OrderItem item = this.beanItemContainer.getItem(e.getItemId()).getBean();
        this.orderService.deleteItem(order.getId(), item.getId(), userId);
        this.beanItemContainer.removeItem(e.getItemId());
    }

}
