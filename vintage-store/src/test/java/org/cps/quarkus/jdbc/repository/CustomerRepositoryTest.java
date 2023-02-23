package org.cps.quarkus.jdbc.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.cps.quarkus.jdbc.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class CustomerRepositoryTest {

    @Inject
    CustomerRepository repository;

    @Test
    @TestTransaction
    public void shouldCreateAndFindACustomer() {
        Customer customer = new Customer("firstName", "secondName", "email");

        repository.persist(customer);
        Assertions.assertNotNull(customer.getId());

        Customer foundCustomer = repository.findById(customer.getId());
        Assertions.assertEquals(customer.getFirstName(), foundCustomer.getFirstName());
    }

}