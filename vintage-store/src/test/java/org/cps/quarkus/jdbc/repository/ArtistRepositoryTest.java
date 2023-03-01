package org.cps.quarkus.jdbc.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.cps.quarkus.jdbc.Artist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class ArtistRepositoryTest {

    @Inject
    ArtistRepository artistRepository;

    @Test
    @TestTransaction
    public void shouldCreateAndFindAnArtist() {

        long count = artistRepository.count();
        long streamCount = artistRepository.streamAll().count();
        Assertions.assertEquals(count, streamCount);
        Assertions.assertEquals(count, artistRepository.listAllArtistsSorted().size());

        Artist artist = new Artist("name", "bio");

        artistRepository.persist(artist);
        Assertions.assertNotNull(artist.getId());
        Assertions.assertEquals(count + 1, artistRepository.count());

        Artist foundArtist = artistRepository.findById(artist.getId());
        Assertions.assertEquals(artist.getName(), foundArtist.getName());
        Assertions.assertEquals(artist.getBio(), foundArtist.getBio());

        artistRepository.deleteById(artist.getId());
        Assertions.assertEquals(count, artistRepository.count());
    }

}