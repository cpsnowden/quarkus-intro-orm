package org.cps.quarkus.jdbc.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "t_purchase_order_lines")
public class OrderLine extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "item_fk")
    public Item item;
    @Column(nullable = false)
    public Integer quantity;
    @ManyToOne
    @JoinColumn(name = "purchase_order_fk")
    public PurchaseOrder purchaseOrder;
    @Column(name = "created_date", nullable = false)
    public Instant createdDate = Instant.now();
}
