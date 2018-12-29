package by.prakapienka.at13java.web.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = OrdersView.VIEW_NAME)
public class OrdersView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "ordersView";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show(event.getParameters());
    }

}
