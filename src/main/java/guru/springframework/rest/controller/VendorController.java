package guru.springframework.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.rest.api.v1.model.VendorDto;
import guru.springframework.rest.api.v1.model.VendorListDto;
import guru.springframework.rest.service.VendorService;

@RestController
@RequestMapping("/api/v1/vendors/")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping()
    public ResponseEntity<VendorListDto> getAllVendors() {
        return new ResponseEntity<VendorListDto>(new VendorListDto(vendorService.getAllVendors()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<VendorDto> getVendorById(@PathVariable Long id) {
        return new ResponseEntity<VendorDto>(vendorService.getVendorById(id), HttpStatus.OK);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<VendorDto> getVendorByName(@PathVariable String name) {
        return new ResponseEntity<VendorDto>(vendorService.getVendorByName(name), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<VendorDto> createNewVendor(@RequestBody VendorDto vendorDto) {
        return new ResponseEntity<VendorDto>(vendorService.createNewVendor(vendorDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<VendorDto> updateVendorById(@PathVariable Long id, @RequestBody VendorDto vendorDto) {
        return new ResponseEntity<VendorDto>(vendorService.updateVendorByDto(id, vendorDto), HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<VendorDto> patchVendorById(@PathVariable Long id, @RequestBody VendorDto vendorDto) {
        return new ResponseEntity<VendorDto>(vendorService.patchVendorByDto(id, vendorDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteVendorById(@PathVariable Long id) {
        vendorService.deleteVendorById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
