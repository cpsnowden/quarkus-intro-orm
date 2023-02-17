package org.cps.quarkus.jdbc.respository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.cps.quarkus.jdbc.Customer;

import javax.enterprise.context.ApplicationScoped;

/**
 * Note that the Customer class exists in another jar, hence require a Panache archive
 */
@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {
}
