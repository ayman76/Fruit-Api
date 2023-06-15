package guru.springframework.rest.service;

import java.util.List;

import guru.springframework.rest.api.v1.model.VendorDto;

public interface VendorService {
    List<VendorDto> getAllVendors();

    VendorDto getVendorById(Long id);

    VendorDto getVendorByName(String name);

    VendorDto createNewVendor(VendorDto vendorDto);

    VendorDto updateVendorByDto(Long id, VendorDto vendorDto);

    VendorDto patchVendorByDto(Long id, VendorDto vendorDto);

    void deleteVendorById(Long id);
}
