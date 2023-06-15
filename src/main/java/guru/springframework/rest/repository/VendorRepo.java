package guru.springframework.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.springframework.rest.domain.Vendor;

public interface VendorRepo extends JpaRepository<Vendor, Long> {
    Vendor findByName(String name);
}
