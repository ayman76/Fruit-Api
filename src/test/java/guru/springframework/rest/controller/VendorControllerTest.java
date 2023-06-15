package guru.springframework.rest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.rest.api.v1.model.VendorDto;
import guru.springframework.rest.domain.Vendor;
import guru.springframework.rest.service.VendorService;
import javafx.scene.media.Media;

public class VendorControllerTest extends AbstractRestControllerTest {

    /**
     *
     */
    private static final Long ID = 1L;

    private static final String VENDORURL = "/api/v1/vendors/1";

    private static final String NAME = "vendor1";

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
    }

    @Test
    void testCreateNewVendor() throws Exception {
        VendorDto vendorDto = new VendorDto();
        vendorDto.setName(NAME);

        VendorDto returnVendor = new VendorDto();
        returnVendor.setId(ID);
        returnVendor.setName(NAME);
        returnVendor.setVendorUrl(VENDORURL);

        when(vendorService.createNewVendor(any(VendorDto.class))).thenReturn(returnVendor);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/vendors/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.equalTo(NAME)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vendorUrl", Matchers.equalTo(VENDORURL)));
    }

    @Test
    void testDeleteVendorById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/vendors/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(vendorService, times(1)).deleteVendorById(anyLong());
    }

    @Test
    void testGetAllVendors() throws Exception {
        List<VendorDto> vendorDtos = Arrays.asList(new VendorDto(), new VendorDto(), new VendorDto());

        when(vendorService.getAllVendors()).thenReturn(vendorDtos);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/vendors/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.vendors", Matchers.hasSize(3)));
    }

    @Test
    void testGetVendorById() throws Exception {
        VendorDto returnVendor = new VendorDto();
        returnVendor.setId(ID);
        returnVendor.setName(NAME);
        returnVendor.setVendorUrl(VENDORURL);

        when(vendorService.getVendorById(anyLong())).thenReturn(returnVendor);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/vendors/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(ID.intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.equalTo(NAME)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vendorUrl", Matchers.equalTo(VENDORURL)));

    }

    @Test
    void testGetVendorByName() throws Exception {
        VendorDto returnVendor = new VendorDto();
        returnVendor.setId(ID);
        returnVendor.setName(NAME);
        returnVendor.setVendorUrl(VENDORURL);

        when(vendorService.getVendorByName(anyString())).thenReturn(returnVendor);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/vendors/name/vendor1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.equalTo(NAME)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vendorUrl", Matchers.equalTo(VENDORURL)));
    }

    @Test
    void testPatchVendorById() throws Exception{
        VendorDto vendorDto = new VendorDto();
        vendorDto.setName(NAME);

        VendorDto returnVendor = new VendorDto();
        returnVendor.setId(ID);
        returnVendor.setName(NAME);
        returnVendor.setVendorUrl(VENDORURL);

        when(vendorService.patchVendorByDto(anyLong(), any(VendorDto.class))).thenReturn(returnVendor);

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/vendors/1")
        .content(asJsonString(vendorDto))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.equalTo(vendorDto.getName())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.vendorUrl", Matchers.equalTo(VENDORURL)));
    }

    @Test
    void testUpdateVendorById() throws Exception{
        VendorDto vendorDto = new VendorDto();
        vendorDto.setName(NAME);

        VendorDto returnVendor = new VendorDto();
        returnVendor.setId(ID);
        returnVendor.setName(NAME);
        returnVendor.setVendorUrl(VENDORURL);

        when(vendorService.updateVendorByDto(anyLong(), any(VendorDto.class))).thenReturn(returnVendor);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/vendors/1")
        .content(asJsonString(vendorDto))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.equalTo(vendorDto.getName())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.vendorUrl", Matchers.equalTo(VENDORURL)));
    }
}
