package org.cps.quarkus.jdbc;

public interface ICustomerRepository {
    void persist(Customer customer);

    Customer findById(Long id);
}
