package ru.geekbrains.persistance;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private BigDecimal cost;


    public Product(Long id, String title, BigDecimal cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public Product(String title, BigDecimal cost) {
        this.title = title;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }
        public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getCost() {
        return cost;
    }
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
