package guru.springframework.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.text.html.Option;

import org.springframework.stereotype.Service;

import guru.springframework.rest.api.v1.mapper.ProductMapper;
import guru.springframework.rest.api.v1.model.ProductDto;
import guru.springframework.rest.domain.Product;
import guru.springframework.rest.domain.Vendor;
import guru.springframework.rest.repository.ProductRepo;
import guru.springframework.rest.repository.VendorRepo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;
    private final VendorRepo vendorRepo;

    public ProductServiceImpl(ProductRepo productRepo, VendorRepo vendorRepo, ProductMapper productMapper) {
        this.productRepo = productRepo;
        this.vendorRepo = vendorRepo;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDto createNewProduct(Long id, ProductDto productDto) {
        Vendor vendor = vendorRepo.findById(id).get();
        Product product = productMapper.productDtoToProduct(productDto);
        product.setVendor(vendor);
        ProductDto savedProduct = productMapper.productToProductDto(productRepo.save(product));
        savedProduct.setProductUrl("/api/v1/product/" + savedProduct.getId());
        return savedProduct;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepo.findAll().stream().map(product -> {
            ProductDto productDto = productMapper.productToProductDto(product);
            productDto.setProductUrl("/api/v1/product/" + product.getId());
            return productDto;
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteProductById(Long vendorId, Long id) {
        Optional<Vendor> foundedVendor = vendorRepo.findById(vendorId);

        if (foundedVendor.isPresent()) {
            Vendor vendor = foundedVendor.get();
            Optional<Product> foundedProduct = productRepo.findById(id);
            if (foundedProduct.isPresent()) {
                Product productToDelete = foundedProduct.get();
                // productRepo.deleteById(id);
                productToDelete.setVendor(null);
                vendor.getProducts().remove(productToDelete);
                vendorRepo.save(vendor);

                log.error("Product Deleted with id: " + productToDelete.getId());
            } else {
                throw new ResourceNotFoundException("Product Not Found");
            }
        }else{
            throw new ResourceNotFoundException("Vendor Not Found");
        }

    }

    @Override
    public ProductDto getProductById(Long id) {
        Optional<Product> foundedProduct = productRepo.findById(id);
        if (foundedProduct.isPresent()) {
            ProductDto returnProduct = productMapper.productToProductDto(foundedProduct.get());
            returnProduct.setProductUrl("/api/v1/product/" + returnProduct.getId());
            return returnProduct;
        } else {
            throw new ResourceNotFoundException("Product Not Found");
        }
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product product = productMapper.productDtoToProduct(productDto);
        product.setId(id);

        ProductDto savedProduct = productMapper.productToProductDto(productRepo.save(product));
        savedProduct.setProductUrl("/api/v1/product/" + savedProduct.getId());
        return savedProduct;
    }

}
