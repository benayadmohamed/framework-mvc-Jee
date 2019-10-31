package framework.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ana {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    public Ana() {
    }

    public Ana(String name) {
        this.name = name;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Ana{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
