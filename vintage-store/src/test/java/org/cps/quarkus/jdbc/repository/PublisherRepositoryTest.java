package org.cps.quarkus.jdbc.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.cps.quarkus.jdbc.model.Publisher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;

@QuarkusTest
public class PublisherRepositoryTest {

    @Test
    @TestTransaction
    public void shouldCreateAndFindAPublisher() {

        long count = Publisher.count();
        long streamCount = Publisher.streamAll().count();
        Assertions.assertEquals(count, streamCount);

        Publisher publisher = new Publisher("name");

        Publisher.persist(publisher);
        Assertions.assertNotNull(publisher.id);
        Assertions.assertEquals(count + 1, Publisher.count());

        Publisher foundPublisher = Publisher.findById(publisher.id);
        Assertions.assertEquals(foundPublisher.name, publisher.name);

        foundPublisher = Publisher.findByName(foundPublisher.name).orElseThrow(EntityNotFoundException::new);
        Assertions.assertEquals(foundPublisher.name, publisher.name);

        Assertions.assertTrue(Publisher.findContainsName("name").size() >= 1);

        Publisher.deleteById(foundPublisher.id);
        Assertions.assertEquals(count, Publisher.count());
    }
}
