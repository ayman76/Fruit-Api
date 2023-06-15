package guru.springframework.rest.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.rest.domain.Category;
import guru.springframework.rest.domain.Customer;
import guru.springframework.rest.domain.Vendor;
import guru.springframework.rest.repository.CategoryRepo;
import guru.springframework.rest.repository.CustomerRepo;
import guru.springframework.rest.repository.VendorRepo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepo categoryRepo;
    private CustomerRepo customerRepo;
    private VendorRepo vendorRepo;

    public Bootstrap(CategoryRepo categoryRepo, CustomerRepo customerRepo, VendorRepo vendorRepo) {
        this.categoryRepo = categoryRepo;
        this.customerRepo = customerRepo;
        this.vendorRepo = vendorRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();

        loadCustomers();

        loadVendors();

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

    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Home Fruits");

        Vendor vendor2 = new Vendor();
        vendor2.setName("Fun Fresh Fruits Ltd.");

        Vendor vendor3 = new Vendor();
        vendor3.setName("Nuts for Nuts Company");

        Vendor vendor4 = new Vendor();
        vendor4.setName("Exotic Fruits Company");

        vendorRepo.save(vendor1);
        vendorRepo.save(vendor2);
        vendorRepo.save(vendor3);
        vendorRepo.save(vendor4);

        log.error("Vendor Loaded =" + vendorRepo.count());
    }

}
