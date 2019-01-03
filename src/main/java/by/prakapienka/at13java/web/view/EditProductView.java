package by.prakapienka.at13java.web.view;

import by.prakapienka.at13java.model.OrderItem;
import by.prakapienka.at13java.service.ProductService;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

@SpringView(name = EditProductView.VIEW_NAME)
public class EditProductView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "editProductView";

    private ProductService productService;

    private BeanItem<OrderItem> beanItem;

    private TextField nameField;
    private Button submitButton;

    @Autowired
    public EditProductView(ProductService productService) {
        this.productService = productService;

        this.nameField = new TextField("Name");

        this.submitButton = new Button("Save", this::onSubmit);

        this.addComponents(this.nameField, this.submitButton);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        final Integer id = StringUtils.isEmpty(event.getParameters()) ? null : Integer.parseInt(event.getParameters());
        final OrderItem product = id != null ? productService.get(id) : new OrderItem();
        this.beanItem = new BeanItem<>(product);
        this.nameField.setPropertyDataSource(this.beanItem.getItemProperty("name"));
    }

    private void onSubmit(Button.ClickEvent e) {
        final OrderItem product = this.beanItem.getBean();
        if (product.isNew()) {
            productService.insert(product);
        } else {
            productService.update(product);
        }
        getUI().getNavigator().navigateTo(ProductsView.VIEW_NAME);
    }

}
