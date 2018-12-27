package by.prakapienka.at13java.web.view;

import by.prakapienka.at13java.model.OrderItem;
import by.prakapienka.at13java.service.ProductService;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProductsView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "productsView";

    private Grid grid;
    private BeanItemContainer<OrderItem> beanItemContainer;

    private ProductService productService;

    @Autowired
    public ProductsView(final ProductService productService) {
        this.productService = productService;

        this.setSizeFull();

        this.beanItemContainer = new BeanItemContainer<>(OrderItem.class);
        this.beanItemContainer.removeContainerProperty("new");

        this.grid = new Grid(this.beanItemContainer);
        addComponent(grid);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        fillData();
    }

    private void fillData() {
        this.beanItemContainer.removeAllItems();
        productService.getAll().forEach(beanItemContainer::addBean);
    }

}
