package by.prakapienka.at13java.web;

import by.prakapienka.at13java.model.Order;
import by.prakapienka.at13java.service.OrderService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class OrderServlet extends HttpServlet {

    private WebApplicationContext applicationContext;
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        orderService = applicationContext.getBean(OrderService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String id = req.getParameter("id");

        if (id == null || id.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/users");
        }
        req.setAttribute("id", id);

        if (action == null) {
            List<Order> orders = orderService.getAll(Integer.valueOf(id));
            if (orders == null) {
                orders = Collections.emptyList();
            }
            req.setAttribute("orders", orders);
            req.getRequestDispatcher("WEB-INF/jsp/orders.jsp").forward(req, resp);
        } else if ("delete".equals(action)) {
            orderService.delete(
                    Integer.valueOf(req.getParameter("order")),
                    Integer.valueOf(req.getParameter("id")));
            resp.sendRedirect("orders?id=" + id);
        } else if ("create".equals(action) || "update".equals(action)) {
            final Order order = "create".equals(action) ?
                    new Order("") :
                    orderService.get(
                            Integer.valueOf(req.getParameter("order")),
                            Integer.valueOf(req.getParameter("id")));
            req.setAttribute("order", order);
            req.getRequestDispatcher("WEB-INF/jsp/editOrder.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id").trim();
        String orderId = req.getParameter("order").trim();

        final Order order = new Order("null".equals(orderId) || orderId.isEmpty() ? null : Integer.valueOf(orderId),
                req.getParameter("name").trim());

        if (id.isEmpty()) {
            orderService.insert(order, Integer.valueOf(id));
        } else {
            orderService.update(order, Integer.valueOf(id));
        }
        resp.sendRedirect("orders?id=" + id);
    }
}
