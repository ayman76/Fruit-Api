package guru.springframework.rest.api.v1.mapper;

import guru.springframework.rest.api.v1.model.VendorDto;
import guru.springframework.rest.domain.Vendor;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;


@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-15T15:41:22+0300",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.34.0.v20230413-0857, environment: Java 17.0.7 (Eclipse Adoptium)"
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

        return vendor;
    }
}
