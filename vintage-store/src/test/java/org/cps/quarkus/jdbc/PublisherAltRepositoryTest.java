package org.cps.quarkus.jdbc;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
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