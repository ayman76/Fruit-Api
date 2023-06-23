package guru.springframework.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.springframework.rest.domain.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{
    
}
