package org.cps.quarkus.jdbc;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PublisherAltRepository implements PanacheRepository<PublisherAlt> {

}