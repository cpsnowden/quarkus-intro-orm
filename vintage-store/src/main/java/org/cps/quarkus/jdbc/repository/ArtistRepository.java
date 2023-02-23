package org.cps.quarkus.jdbc.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.cps.quarkus.jdbc.Artist;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArtistRepository implements PanacheRepository<Artist> {
}