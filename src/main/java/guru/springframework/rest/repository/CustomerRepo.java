package guru.springframework.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.springframework.rest.domain.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    Customer findByFirstName(String firstName);
    Customer findByLastName(String LastName);

}
