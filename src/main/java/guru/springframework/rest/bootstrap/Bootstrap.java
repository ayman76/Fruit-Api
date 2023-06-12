package guru.springframework.rest.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.rest.domain.Category;
import guru.springframework.rest.domain.Customer;
import guru.springframework.rest.repository.CategoryRepo;
import guru.springframework.rest.repository.CustomerRepo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepo categoryRepo;
    private CustomerRepo customerRepo;

    public Bootstrap(CategoryRepo categoryRepo, CustomerRepo customerRepo) {
        this.categoryRepo = categoryRepo;
        this.customerRepo = customerRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();

        loadCustomers();

    }

    private void loadCategories() {
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

        log.error("Categories Loaded = " + categoryRepo.count());
    }

    private void loadCustomers() {
        Customer ayman = new Customer();
        ayman.setFirstName("Ayman");
        ayman.setLastName("Mohamed");

        Customer mohamed = new Customer();
        mohamed.setFirstName("Mohamed");
        mohamed.setLastName("Mahmoud");

        Customer khaled = new Customer();
        khaled.setFirstName("Khaled");
        khaled.setLastName("Ahmed");

        customerRepo.save(ayman);
        customerRepo.save(mohamed);
        customerRepo.save(khaled);

        log.error("Customer Loaded = " + customerRepo.count());
    }

}
