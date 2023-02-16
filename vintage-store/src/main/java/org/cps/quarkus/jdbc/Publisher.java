package org.cps.quarkus.jdbc;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import java.time.Instant;

public class Publisher extends PanacheEntity {
    public String name;
    public Instant createdDate = Instant.now();

    public Publisher() {
    }

    public Publisher(String name) {
        this.name = name;
    }
}