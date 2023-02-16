package org.cps.quarkus.jdbc;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.sql.SQLException;

@QuarkusTest
public class ArtistRepositoryTest {

    @Inject
    IArtistRepository artistRepository;
    @Test
    public void shouldCreateAndFindAnArtist() throws SQLException {
        Artist artist = new Artist("name", "bio");

        artistRepository.persist(artist);
        Assertions.assertNotNull(artist.getId());

        Artist foundArtist = artistRepository.findById(artist.getId());
        Assertions.assertEquals(artist.getName(), foundArtist.getName());
        Assertions.assertEquals(artist.getBio(), foundArtist.getBio());
    }

}