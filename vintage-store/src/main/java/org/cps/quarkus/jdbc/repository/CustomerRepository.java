package org.cps.quarkus.jdbc.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import org.cps.quarkus.jdbc.Customer;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

/**
 * Note that the Customer class exists in another jar, hence require a Panache archive
 */
@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {

    public List<Customer> listAllDans() {
        return list("firstName = 'Dan'", Sort.by("lastName"));
    }
}
