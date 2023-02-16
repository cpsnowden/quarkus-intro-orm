package org.cps.quarkus.jdbc;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class CustomerRepository implements ICustomerRepository {

    @Inject
    EntityManager em;

    @Override
    public void persist(Customer customer) {
        em.persist(customer);
    }

    @Override
    public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }

}
