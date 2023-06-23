package guru.springframework.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.rest.api.v1.model.ProductDto;
import guru.springframework.rest.api.v1.model.ProductListDto;
import guru.springframework.rest.service.ProductService;

@RestController
@RequestMapping("/api/v1/")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("product/")
    public ResponseEntity<ProductListDto> getAllProducts() {
        return new ResponseEntity<ProductListDto>(new ProductListDto(productService.getAllProducts()), HttpStatus.OK);
    }

    @PostMapping("vendors/{id}/product")
    public ResponseEntity<ProductDto> createNewProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return new ResponseEntity<ProductDto>(productService.createNewProduct(id, productDto), HttpStatus.CREATED);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return new ResponseEntity<ProductDto>(productService.getProductById(id), HttpStatus.OK);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDto> updateProductById(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return new ResponseEntity<ProductDto>(productService.updateProduct(id, productDto), HttpStatus.OK);
    }

    @DeleteMapping("vendors/{vendorId}/product/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long vendorId, @PathVariable Long id) {
        productService.deleteProductById(vendorId, id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
