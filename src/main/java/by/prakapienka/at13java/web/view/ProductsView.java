package by.prakapienka.at13java.web.view;

import by.prakapienka.at13java.model.OrderItem;
import by.prakapienka.at13java.service.ProductService;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.ClickableRenderer;
import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name = ProductsView.VIEW_NAME)
public class ProductsView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "productsView";

    private Grid grid;
    private GeneratedPropertyContainer propertyContainer;
    private BeanItemContainer<OrderItem> beanItemContainer;

    private ProductService productService;

    @Autowired
    public ProductsView(final ProductService productService) {
        this.productService = productService;

        this.setSizeFull();

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

        this.grid = new Grid(this.propertyContainer);
        this.grid
                .getColumn("delete")
                .setRenderer(new ButtonRenderer(this::onDelete));
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

    private void onDelete(ClickableRenderer.RendererClickEvent e) {
        final OrderItem user = this.beanItemContainer.getItem(e.getItemId()).getBean();
        this.productService.delete(user.getId());
        this.beanItemContainer.removeItem(e.getItemId());
    }

}
