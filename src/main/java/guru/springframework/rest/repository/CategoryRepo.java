package guru.springframework.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.springframework.rest.domain.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {

}
