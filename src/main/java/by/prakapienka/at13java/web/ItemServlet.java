package by.prakapienka.at13java.web;

import by.prakapienka.at13java.model.OrderItem;
import by.prakapienka.at13java.service.OrderService;
import by.prakapienka.at13java.service.ProductService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ItemServlet extends HttpServlet {

    private WebApplicationContext applicationContext;
    private OrderService orderService;
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        orderService = applicationContext.getBean(OrderService.class);
        productService = applicationContext.getBean(ProductService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        String orderId = req.getParameter("order");

        if (id == null || id.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/users");
        }
        if (orderId == null || orderId.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/orders?id=" + id);
        }

        req.setAttribute("id", id);
        req.setAttribute("order", orderId);

        if (action == null) {
            Set<OrderItem> items = orderService.getWithItems(
                    Integer.valueOf(orderId),
                    Integer.valueOf(id)).getOrderItems();
            if (items == null) {
                items = Collections.emptySet();
            }
            req.setAttribute("items", items);
            req.getRequestDispatcher("WEB-INF/jsp/items.jsp").forward(req, resp);
        } else if ("delete".equals(action)) {
            orderService.deleteItem(
                    Integer.valueOf(req.getParameter("order")),
                    Integer.valueOf(req.getParameter("item")),
                    Integer.valueOf(req.getParameter("id")));
            resp.sendRedirect("items?id=" + id + "&order=" + orderId);
        } else if ("add".equals(action)) {
            List<OrderItem> products = productService.getAll();
            if (products == null) {
                products = Collections.emptyList();
            }
            req.setAttribute("products", products);
            req.getRequestDispatcher("WEB-INF/jsp/addItem.jsp").forward(req, resp);
        } else if ("doAdd".equals(action)) {
            orderService.insertItem(
                    Integer.valueOf(orderId),
                    Integer.valueOf(req.getParameter("item")),
                    Integer.valueOf(id)
            );
            resp.sendRedirect("items?id=" + id + "&order=" + orderId);
        }
    }
}
