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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "This is Vendor Controller")
@RestController
@RequestMapping("/api/v1/vendors/")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "This will get list of vendors", notes = "These are some notes about the API")
    @GetMapping()
    public ResponseEntity<VendorListDto> getAllVendors() {
        return new ResponseEntity<VendorListDto>(new VendorListDto(vendorService.getAllVendors()), HttpStatus.OK);
    }

    @ApiOperation(value = "This will get vendor object with given id", notes = "These are some notes about the API")
    @GetMapping("{id}")
    public ResponseEntity<VendorDto> getVendorById(@PathVariable Long id) {
        return new ResponseEntity<VendorDto>(vendorService.getVendorById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "This will get vendor object with given name", notes = "These are some notes about the API")
    @GetMapping("name/{name}")
    public ResponseEntity<VendorDto> getVendorByName(@PathVariable String name) {
        return new ResponseEntity<VendorDto>(vendorService.getVendorByName(name), HttpStatus.OK);
    }

    @ApiOperation(value = "This will create new vendor", notes = "These are some notes about the API")

    @PostMapping()
    public ResponseEntity<VendorDto> createNewVendor(@RequestBody VendorDto vendorDto) {
        return new ResponseEntity<VendorDto>(vendorService.createNewVendor(vendorDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "This will update vendor with given id", notes = "These are some notes about the API")
    @PutMapping("{id}")
    public ResponseEntity<VendorDto> updateVendorById(@PathVariable Long id, @RequestBody VendorDto vendorDto) {
        return new ResponseEntity<VendorDto>(vendorService.updateVendorByDto(id, vendorDto), HttpStatus.OK);
    }

    @ApiOperation(value = "This will update vendor object with given id", notes = "These are some notes about the API")
    @PatchMapping("{id}")
    public ResponseEntity<VendorDto> patchVendorById(@PathVariable Long id, @RequestBody VendorDto vendorDto) {
        return new ResponseEntity<VendorDto>(vendorService.patchVendorByDto(id, vendorDto), HttpStatus.OK);
    }

    @ApiOperation(value = "This will delete vendor object with given id", notes = "These are some notes about the API")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteVendorById(@PathVariable Long id) {
        vendorService.deleteVendorById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
