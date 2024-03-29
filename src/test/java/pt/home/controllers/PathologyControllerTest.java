package pt.home.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pt.home.api.v1.model.PathologyDTO;
import pt.home.controllers.v1.PathologyController;
import pt.home.services.PathologyService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PathologyControllerTest {

    public static final String NAME_1 = "Pathology name";
    public static final String NAME_2 = "Pathology2 name";

    @Mock
    PathologyService pathologyService;

    @InjectMocks
    PathologyController pathologyController;

    MockMvc mockMvc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(pathologyController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void testGetAllPathologies() throws Exception{

        //given
        PathologyDTO pathologyDTO1 = PathologyDTO.builder().id(1L).name(NAME_1).build();
        PathologyDTO pathologyDTO2 = PathologyDTO.builder().id(2L).name(NAME_2).build();
        List<PathologyDTO> pathologyDTOs = Arrays.asList(pathologyDTO1, pathologyDTO2);

        when(pathologyService.getAllPathologies()).thenReturn(pathologyDTOs);

        //then
        mockMvc.perform(get(PathologyController.BASE_URL)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pathologies", hasSize(2)));

    }

    @Test
    public void testGePatholyByName() throws Exception{

        //given
        PathologyDTO pathologyDTO = PathologyDTO.builder().id(1L).name(NAME_1).build();

        when(pathologyService.getPathologyByName(anyString())).thenReturn(pathologyDTO);

        //then
        mockMvc.perform(get(PathologyController.BASE_URL + "/name")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME_1)));
    }
}
