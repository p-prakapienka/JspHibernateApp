package by.prakapienka.at13java.web.view;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import org.springframework.stereotype.Controller;

@Controller
public class StartView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "";

    private Navigator navigator;

    public StartView() {
        setSizeFull();

        final Button usersBtn = new Button("Users", e -> {
            navigator.navigateTo(UsersView.VIEW_NAME);
        });
        final Button productsBtn = new Button("Products", e -> {
            navigator.navigateTo(ProductsView.VIEW_NAME);
        });

        addComponents(usersBtn, productsBtn);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    }

    public void setNavigator(final Navigator navigator) {
        this.navigator = navigator;
    }

}
