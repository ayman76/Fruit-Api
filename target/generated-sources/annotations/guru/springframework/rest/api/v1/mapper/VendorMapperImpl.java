package guru.springframework.rest.api.v1.mapper;

import guru.springframework.rest.api.v1.model.ProductDto;
import guru.springframework.rest.api.v1.model.VendorDto;
import guru.springframework.rest.domain.Product;
import guru.springframework.rest.domain.Vendor;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-23T18:13:19+0300",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.34.0.v20230511-1142, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class VendorMapperImpl implements VendorMapper {

    @Override
    public VendorDto vendorToVendorDto(Vendor vendor) {
        if ( vendor == null ) {
            return null;
        }

        VendorDto vendorDto = new VendorDto();

        vendorDto.setId( vendor.getId() );
        vendorDto.setName( vendor.getName() );
        vendorDto.setProducts( productListToProductDtoList( vendor.getProducts() ) );

        return vendorDto;
    }

    @Override
    public Vendor vendorDtoToVendor(VendorDto vendorDto) {
        if ( vendorDto == null ) {
            return null;
        }

        Vendor vendor = new Vendor();

        vendor.setId( vendorDto.getId() );
        vendor.setName( vendorDto.getName() );
        vendor.setProducts( productDtoListToProductList( vendorDto.getProducts() ) );

        return vendor;
    }

    protected ProductDto productToProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId( product.getId() );
        productDto.setName( product.getName() );
        productDto.setPrice( product.getPrice() );

        return productDto;
    }

    protected List<ProductDto> productListToProductDtoList(List<Product> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductDto> list1 = new ArrayList<ProductDto>( list.size() );
        for ( Product product : list ) {
            list1.add( productToProductDto( product ) );
        }

        return list1;
    }

    protected Product productDtoToProduct(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productDto.getId() );
        product.setName( productDto.getName() );
        product.setPrice( productDto.getPrice() );

        return product;
    }

    protected List<Product> productDtoListToProductList(List<ProductDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Product> list1 = new ArrayList<Product>( list.size() );
        for ( ProductDto productDto : list ) {
            list1.add( productDtoToProduct( productDto ) );
        }

        return list1;
    }
}
