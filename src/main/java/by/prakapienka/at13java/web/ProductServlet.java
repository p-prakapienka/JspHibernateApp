package by.prakapienka.at13java.web;

import by.prakapienka.at13java.model.OrderItem;
import by.prakapienka.at13java.service.ProductService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ProductServlet extends HttpServlet {

    private ConfigurableApplicationContext applicationContext;
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        applicationContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        productService = applicationContext.getBean(ProductService.class);
    }

    @Override
    public void destroy() {
        applicationContext.close();
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            List<OrderItem> products = productService.getAll();
            if (products == null) {
                products = Collections.emptyList();
            }
            req.setAttribute("products", products);
            req.getRequestDispatcher("WEB-INF/jsp/products.jsp").forward(req, resp);
        } else if ("delete".equals(action)) {
            productService.delete(Integer.valueOf(req.getParameter("id")));
            resp.sendRedirect("products");
        } else if ("create".equals(action) || "update".equals(action)) {
            final OrderItem product = "create".equals(action) ?
                    new OrderItem("") :
                    productService.get(Integer.valueOf(req.getParameter("id")));
            req.setAttribute("product", product);
            req.getRequestDispatcher("WEB-INF/jsp/editProduct.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id").trim();

        final OrderItem product = new OrderItem("null".equals(id) || id.isEmpty() ? null : Integer.valueOf(id),
                req.getParameter("name").trim());

        if (id.isEmpty()) {
            productService.insert(product);
        } else {
            productService.update(product);
        }
        resp.sendRedirect("products");
    }
}
