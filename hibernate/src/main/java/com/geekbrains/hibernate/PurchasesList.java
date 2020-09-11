package com.geekbrains.hibernate;

import javax.persistence.*;

@Entity
@Table(name = "purchList")
public class PurchasesList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "buyer_id")
    private int buyer_id;

    @Column(name = "product_id")
    private int product_id;

    public PurchasesList() {
    }

    public PurchasesList(int idBuyer, int idProduct) {
        this.buyer_id = idBuyer;
        this.product_id = idProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(int buyer_id) {
        this.buyer_id = buyer_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
