package by.prakapienka.at13java.web.view;

import by.prakapienka.at13java.model.Order;
import by.prakapienka.at13java.service.OrderService;
import by.prakapienka.at13java.web.attribute.SessionAttribute;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

@SpringView(name = EditOrderView.VIEW_NAME)
public class EditOrderView extends FormLayout implements View {

    public static final String VIEW_NAME = "editOrderView";

    private OrderService orderService;

    private BeanItem<Order> item;

    private TextField nameField;
    private Button submitButton;

    @Autowired
    public EditOrderView(OrderService orderService) {
        this.orderService = orderService;

        this.nameField = new TextField("Name");

        this.submitButton = new Button("Save", this::onSubmit);

        this.addComponents(this.nameField, this.submitButton);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        final Integer id = StringUtils.isEmpty(event.getParameters()) ? null : Integer.parseInt(event.getParameters());
        final Order order = id != null
                ? orderService.get(id,
                                   (Integer) getSession().getAttribute(SessionAttribute.USER_ID_ATTR))
                : new Order();
        this.item = new BeanItem<>(order);
        this.nameField.setPropertyDataSource(this.item.getItemProperty("name"));
    }

    private void onSubmit(Button.ClickEvent e) {
        final Order order = this.item.getBean();
        if (order.isNew()) {
            orderService.insert(order, (Integer) getSession().getAttribute(SessionAttribute.USER_ID_ATTR));
        } else {
            orderService.update(order, order.getUser().getId());
        }
        getUI().getNavigator().navigateTo(OrdersView.VIEW_NAME + "/"
                + getSession().getAttribute(SessionAttribute.USER_ID_ATTR));
    }

}
