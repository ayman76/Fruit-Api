package guru.springframework.rest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.rest.api.v1.mapper.VendorMapper;
import guru.springframework.rest.api.v1.model.VendorDto;
import guru.springframework.rest.domain.Vendor;
import guru.springframework.rest.repository.VendorRepo;

public class VendorServiceImplTest {

    private static final long ID = 1L;

    private static final String NAME = "vendor1";

    @Mock
    VendorRepo vendorRepo;

    VendorService vendorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        vendorService = new VendorServiceImpl(vendorRepo, VendorMapper.INSTANCE);
    }

    @Test
    void testCreateNewVendor() {
        // given
        VendorDto vendorDto = new VendorDto();
        vendorDto.setName(NAME);

        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        when(vendorRepo.save(any())).thenReturn(vendor);

        // when
        VendorDto savedVendorDto = vendorService.createNewVendor(vendorDto);

        assertEquals(Long.valueOf(ID), savedVendorDto.getId());
        assertEquals(vendorDto.getName(), savedVendorDto.getName());
        assertEquals("/api/v1/vendors/1", savedVendorDto.getVendorUrl());
    }

    @Test
    void testDeleteVendorById() {

        vendorRepo.deleteById(ID);

        verify(vendorRepo, times(1)).deleteById(anyLong());

    }

    @Test
    void testGetAllVendors() {
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor(), new Vendor());

        when(vendorRepo.findAll()).thenReturn(vendors);

        // when
        List<VendorDto> vendorDtos = vendorService.getAllVendors();

        assertEquals(3, vendorDtos.size());
    }

    @Test
    void testGetVendorById() {
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        when(vendorRepo.findById(anyLong())).thenReturn(Optional.of(vendor));

        // when
        VendorDto foundedVendor = vendorService.getVendorById(ID);

        assertEquals(Long.valueOf(ID), foundedVendor.getId());
        assertEquals(NAME, foundedVendor.getName());

    }

    @Test
    void testGetVendorByName() {
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        when(vendorRepo.findByName(anyString())).thenReturn(vendor);

        // when
        VendorDto foundedVendor = vendorService.getVendorByName(NAME);

        assertEquals(Long.valueOf(ID), foundedVendor.getId());
        assertEquals(NAME, foundedVendor.getName());
    }

    @Test
    void testUpdateVendorByDto() {
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        VendorDto vendorDto = new VendorDto();
        vendorDto.setName(NAME);

        when(vendorRepo.save(any(Vendor.class))).thenReturn(vendor);

        //when
        VendorDto updatedVendor = vendorService.updateVendorByDto(ID, vendorDto);

        assertEquals(vendorDto.getName(), updatedVendor.getName());
        assertEquals("/api/v1/vendors/1", updatedVendor.getVendorUrl());
    }
}
