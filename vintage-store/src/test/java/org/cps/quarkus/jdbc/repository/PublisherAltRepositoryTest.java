package org.cps.quarkus.jdbc.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.cps.quarkus.jdbc.model.PublisherAlt;
import org.cps.quarkus.jdbc.respository.PublisherAltRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class PublisherAltRepositoryTest {

    @Inject
    PublisherAltRepository repository;

    @Test
    @TestTransaction
    public void shouldCreateAndFindAPublisher() {
        PublisherAlt publisher = new PublisherAlt("name");

        repository.persist(publisher);
        Assertions.assertNotNull(publisher.getId());

        PublisherAlt foundPublisher = repository.findById(publisher.getId());
        Assertions.assertEquals(foundPublisher.getName(), publisher.getName());
    }
}