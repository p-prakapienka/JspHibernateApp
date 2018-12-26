package by.prakapienka.at13java.web.view;

import by.prakapienka.at13java.service.ProductService;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProductsView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "productsView";

    private Navigator navigator;

    private Grid grid;

    private ProductService productService;

    @Autowired
    public ProductsView(final ProductService productService) {
        this.productService = productService;

        this.setSizeFull();

        this.grid = new Grid();
        grid.addColumn("id", Integer.class);
        grid.addColumn("name", String.class);
        addComponent(grid);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        fillData();
    }

    public void setNavigator(final Navigator navigator) {
        this.navigator = navigator;
    }

    private void fillData() {
        grid.getContainerDataSource().removeAllItems();
        productService.getAll().forEach(p -> grid.addRow(p.getId(), p.getName()));
    }

}
