package guru.springframework.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import guru.springframework.rest.api.v1.mapper.VendorMapper;
import guru.springframework.rest.api.v1.model.ProductDto;
import guru.springframework.rest.api.v1.model.VendorDto;
import guru.springframework.rest.domain.Vendor;
import guru.springframework.rest.repository.VendorRepo;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepo vendorRepo;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepo vendorRepo, VendorMapper vendorMapper) {
        this.vendorRepo = vendorRepo;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public VendorDto createNewVendor(VendorDto vendorDto) {
        Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDto);
        Vendor savedVendor = vendorRepo.save(vendor);

        VendorDto returnVendorDto = vendorMapper.vendorToVendorDto(savedVendor);
        returnVendorDto.setVendorUrl("/api/v1/vendors/" + savedVendor.getId());
        return returnVendorDto;
    }

    @Override
    public void deleteVendorById(Long id) {
        Optional<Vendor> vendor = vendorRepo.findById(id);
        if (vendor.isPresent()) {
            vendorRepo.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Vendor Not Found");
        }

    }

    @Override
    public List<VendorDto> getAllVendors() {
        return vendorRepo.findAll()
                .stream()
                .map(
                        vendor -> {
                            VendorDto vendorDto = vendorMapper.vendorToVendorDto(vendor);
                            vendorDto.setVendorUrl("/api/v1/vendors/" + vendor.getId());
                            List<ProductDto> productDtos = new ArrayList<>();

                            vendorDto.setProducts(vendorDto.getProducts().stream().map(productDto -> {
                                productDto.setProductUrl("/api/v1/product/" + productDto.getId());
                                return productDto;
                            }).collect(Collectors.toList()));
                            return vendorDto;
                        })
                .collect(Collectors.toList());
    }

    @Override
    public VendorDto getVendorById(Long id) {
        Vendor vendor = vendorRepo.findById(id).get();
        if (vendor != null) {
            VendorDto vendorDto = vendorMapper.vendorToVendorDto(vendor);
            vendorDto.setVendorUrl("/api/v1/vendors/" + vendor.getId());
            return vendorDto;
        } else {
            throw new ResourceNotFoundException("Vendor Not Found");
        }

    }

    @Override
    public VendorDto getVendorByName(String name) {
        Vendor vendor = vendorRepo.findByName(name);
        if (vendor != null) {
            VendorDto vendorDto = vendorMapper.vendorToVendorDto(vendor);
            vendorDto.setVendorUrl("/api/v1/vendors/" + vendor.getId());
            return vendorDto;

        } else {
            throw new ResourceNotFoundException("Vendor Not Found");
        }
    }

    @Override
    public VendorDto patchVendorByDto(Long id, VendorDto vendorDto) {
        return vendorRepo.findById(id).map(
                vendor -> {
                    if (vendorDto.getName() != null) {
                        vendor.setName(vendorDto.getName());
                    }
                    VendorDto updatedVendorDto = vendorMapper.vendorToVendorDto(vendorRepo.save(vendor));
                    updatedVendorDto.setVendorUrl("/api/v1/vendors/" + updatedVendorDto.getId());
                    return updatedVendorDto;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDto updateVendorByDto(Long id, VendorDto vendorDto) {
        Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDto);
        vendor.setId(id);

        Vendor updatedVendor = vendorRepo.save(vendor);

        VendorDto returnVendorDto = vendorMapper.vendorToVendorDto(updatedVendor);
        returnVendorDto.setVendorUrl("/api/v1/vendors/" + updatedVendor.getId());
        return returnVendorDto;
    }

}
