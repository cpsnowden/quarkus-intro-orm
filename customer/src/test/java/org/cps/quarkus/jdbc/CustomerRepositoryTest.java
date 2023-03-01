package org.cps.quarkus.jdbc;

import io.quarkus.test.junit.QuarkusTest;

import javax.inject.Inject;

@QuarkusTest
public class CustomerRepositoryTest {

    @Inject
    CustomerRepository repository;

}