package by.prakapienka.at13java.web;

import by.prakapienka.at13java.model.User;
import by.prakapienka.at13java.service.UserService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

public class UserServlet extends HttpServlet {

    private ConfigurableApplicationContext applicationContext;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        applicationContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        userService = applicationContext.getBean(UserService.class);
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
            List<User> users = userService.getAll();
            if (users == null) {
                users = Collections.emptyList();
            }
            req.setAttribute("users", users);
            req.getRequestDispatcher("WEB-INF/jsp/users.jsp").forward(req, resp);
        } else if ("delete".equals(action)) {
            userService.delete(Integer.valueOf(req.getParameter("id")));
            resp.sendRedirect("users");
        } else if ("create".equals(action) || "update".equals(action)) {
            final User user = "create".equals(action) ?
                    new User("") :
                    userService.get(Integer.valueOf(req.getParameter("id")));
            req.setAttribute("user", user);
            req.getRequestDispatcher("WEB-INF/jsp/editUser.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id").trim();

        final User user = new User("null".equals(id) || id.isEmpty() ? null : Integer.valueOf(id),
                                   req.getParameter("name").trim());

        if (id.isEmpty()) {
            userService.insert(user);
        } else {
            userService.update(user);
        }
        resp.sendRedirect("users");
    }
}
