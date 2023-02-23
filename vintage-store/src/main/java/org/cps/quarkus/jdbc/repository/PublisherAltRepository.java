package org.cps.quarkus.jdbc.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.cps.quarkus.jdbc.model.PublisherAlt;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PublisherAltRepository implements PanacheRepository<PublisherAlt> {

}