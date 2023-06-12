package guru.springframework.rest.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.rest.domain.Category;
import guru.springframework.rest.repository.CategoryRepo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepo categoryRepo;

    public Bootstrap(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepo.save(fruits);
        categoryRepo.save(dried);
        categoryRepo.save(fresh);
        categoryRepo.save(exotic);
        categoryRepo.save(nuts);

        log.error("Data Loaded = " + categoryRepo.count());

    }

}
