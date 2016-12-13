package by.prakapienka.at13java.dao.jpa;

import by.prakapienka.at13java.dao.OrderDao;
import by.prakapienka.at13java.model.Order;
import by.prakapienka.at13java.model.OrderItem;
import by.prakapienka.at13java.model.User;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaOrderDao implements OrderDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Order save(Order order, int userId) {
        if (!order.isNew() && get(order.getId(), userId) == null) {
            return null;
        }
        order.setUser(em.getReference(User.class, userId));
        if (order.isNew()) {
            em.persist(order);
            return order;
        } else {
            return em.merge(order);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Order.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Order get(int id, int userId) {
        Order order = em.find(Order.class, id);
        return order != null && order.getUser().getId() == userId ? order : null;
    }

    @Override
    public Order getWithItems(int id, int userId) {
        Order order = get(id, userId);
        Hibernate.initialize(order.getOrderItems());
        return order;
    }

    @Override
    public List<Order> getAll(int userId) {
        return em.createNamedQuery(Order.ALL, Order.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    @Transactional
    public Order insertItem(int id, int itemId, int userId) {
        Order order = get(id, userId);
        OrderItem item = em.getReference(OrderItem.class, itemId);
        order.getOrderItems().add(item);
        return em.merge(order);
    }

    @Override
    @Transactional
    public Order deleteItem(int id, int itemId, int userId) {
            Order order = getWithItems(id, userId);
            OrderItem item = em.getReference(OrderItem.class, itemId);
            order.getOrderItems().remove(item);
            return em.merge(order);
    }
}
