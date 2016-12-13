package by.prakapienka.at13java.dao.jpa;

import by.prakapienka.at13java.dao.OrderItemDao;
import by.prakapienka.at13java.model.OrderItem;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaOrderItemDao implements OrderItemDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public OrderItem save(OrderItem item) {
        if (item.isNew()) {
            em.persist(item);
            return item;
        } else {
            return em.merge(item);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(OrderItem.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public OrderItem get(int id) {
        return em.find(OrderItem.class, id);
    }

    @Override
    public List<OrderItem> getAll() {
        return em.createNamedQuery(OrderItem.ALL, OrderItem.class)
                .getResultList();
    }
}
