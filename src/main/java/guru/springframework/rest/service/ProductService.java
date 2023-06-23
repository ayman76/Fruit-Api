package guru.springframework.rest.service;

import java.util.List;

import guru.springframework.rest.api.v1.model.ProductDto;

public interface ProductService {
    
    List<ProductDto> getAllProducts();
    ProductDto createNewProduct(Long vendorId, ProductDto productdDto);
    ProductDto getProductById(Long id);
    ProductDto updateProduct(Long id, ProductDto productDto);
    void deleteProductById(Long vendorId ,Long id);
}
