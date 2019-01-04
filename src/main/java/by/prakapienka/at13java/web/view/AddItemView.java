package by.prakapienka.at13java.web.view;

import by.prakapienka.at13java.model.Order;
import by.prakapienka.at13java.model.OrderItem;
import by.prakapienka.at13java.service.OrderService;
import by.prakapienka.at13java.service.ProductService;
import by.prakapienka.at13java.web.attribute.SessionAttribute;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name = AddItemView.VIEW_NAME)
public class AddItemView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "addItemView";

    private OrderService orderService;
    private ProductService productService;

    private BeanItemContainer<OrderItem> beanItemContainer;
    private Grid grid;

    private Order order;

    @Autowired
    public AddItemView(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;

        this.beanItemContainer = new BeanItemContainer<>(OrderItem.class);
        this.beanItemContainer.removeContainerProperty("new");

        this.grid = new Grid(this.beanItemContainer);
        this.grid.setSelectionMode(Grid.SelectionMode.NONE);
        this.grid.addItemClickListener(this::onRowClick);
        addComponent(grid);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        final Integer userId = (Integer) getSession().getAttribute(SessionAttribute.USER_ID_ATTR);
        this.order = orderService.getWithItems(Integer.parseInt(event.getParameters()), userId);
        this.beanItemContainer.removeAllItems();
        this.beanItemContainer.addAll(productService.getAll());
    }

    private void onRowClick(ItemClickEvent itemClickEvent) {
        final Integer userId = (Integer) getSession().getAttribute(SessionAttribute.USER_ID_ATTR);
        final OrderItem item = this.beanItemContainer.getItem(itemClickEvent.getItemId()).getBean();
        this.orderService.insertItem(this.order.getId(), item.getId(), userId);
        getUI().getNavigator().navigateTo(ItemsView.VIEW_NAME + "/" + order.getId());
    }

}
