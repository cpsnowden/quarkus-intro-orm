package org.cps.quarkus.jdbc.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.cps.quarkus.jdbc.Artist;
import org.cps.quarkus.jdbc.IArtistRepository;
import org.cps.quarkus.jdbc.respository.ArtistRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.sql.SQLException;

@QuarkusTest
public class ArtistRepositoryTest {

    @Inject
    ArtistRepository artistRepository;

    @Test
    @TestTransaction
    public void shouldCreateAndFindAnArtist() {
        Artist artist = new Artist("name", "bio");

        artistRepository.persist(artist);
        Assertions.assertNotNull(artist.getId());

        Artist foundArtist = artistRepository.findById(artist.getId());
        Assertions.assertEquals(artist.getName(), foundArtist.getName());
        Assertions.assertEquals(artist.getBio(), foundArtist.getBio());
    }

}