package by.prakapienka.at13java.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = Order.ALL, query = "SELECT o FROM Order o WHERE o.user.id=:userId ORDER BY o.name"),
        @NamedQuery(name = Order.DELETE, query = "DELETE FROM Order o WHERE o.id=:id AND o.user.id=:userId"),
})
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    public static final String DELETE = "Order.delete";
    public static final String ALL = "Order.getAll";

    @Column(name = "name", nullable = false)
    @NotEmpty
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "orders_products",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private Set<OrderItem> orderItems = new HashSet<>();

    public Order() {}

    public Order(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public Order(String name) {
        this.name = name;
    }

    public Order(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public Order(Integer id, String name, User user) {
        super(id);
        this.name = name;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o) && this.getName().equals(((Order)o).getName());
    }

    @Override
    public String toString() {
        return "Order " + getId() +
                ": name='" + getName() + '.';
    }
}
