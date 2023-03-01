package org.cps.quarkus.jdbc.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import org.cps.quarkus.jdbc.Artist;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ArtistRepository implements PanacheRepository<Artist> {

    public List<Artist> listAllArtistsSorted() {
        return listAll(Sort.descending("name"));
    }



}
