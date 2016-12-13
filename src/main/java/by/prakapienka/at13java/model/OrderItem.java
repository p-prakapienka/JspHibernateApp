package by.prakapienka.at13java.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = OrderItem.ALL, query = "SELECT i FROM OrderItem i ORDER BY i.name"),
        @NamedQuery(name = OrderItem.DELETE, query = "DELETE FROM OrderItem i WHERE i.id=:id")
})
@Entity
@Table(name = "products")
public class OrderItem extends BaseEntity {

    public static final String DELETE = "OrderItem.delete";
    public static final String ALL = "OrderItem.getAll";

    @Column(name = "name", nullable = false)
    @NotEmpty
    private String name;

    public OrderItem() {}

    public OrderItem(String name) {
        this.name = name;
    }

    public OrderItem(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o) && this.getName().equals(((OrderItem)o).getName());
    }

    @Override
    public String toString() {
        return "Product " + getId() +
                ": name='" + getName() + '.';
    }

}
