package org.cps.quarkus.jdbc;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class PublisherRepositoryTest {

    @Test
    @TestTransaction
    public void shouldCreateAndFindAPublisher() {
        Publisher publisher = new Publisher("name");

        Publisher.persist(publisher);
        Assertions.assertNotNull(publisher.id);

        Publisher foundPublisher = Publisher.findById(publisher.id);
        Assertions.assertEquals(foundPublisher.name, publisher.name);
    }
}
