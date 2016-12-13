package by.prakapienka.at13java.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.ALL, query = "SELECT u FROM User u ORDER BY u.name"),
        @NamedQuery(name = User.DELETE_ALL, query = "DELETE FROM User u"),
})
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    public static final String DELETE = "User.delete";
    public static final String ALL = "User.getAll";
    public static final String DELETE_ALL = "User.deleteAll";

    @Column(name = "name", nullable = false)
    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o) && this.getName().equals(((User)o).getName());
    }

    @Override
    public String toString() {
        return "User " + getId() +
                ": name='" + getName() + '.';
    }

}
