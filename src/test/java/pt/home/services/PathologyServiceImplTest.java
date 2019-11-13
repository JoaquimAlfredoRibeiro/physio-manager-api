package pt.home.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.home.api.v1.mapper.PathologyMapper;
import pt.home.api.v1.model.PathologyDTO;
import pt.home.domain.Pathology;
import pt.home.repositories.PathologyRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class PathologyServiceImplTest {

    public static final Long ID = 1L;
    public static final String NAME = "Pathology name";
    public static final String DESCRIPTION = "Description";

    PathologyService pathologyService;

    @Mock
    PathologyRepository pathologyRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        pathologyService = new PathologyServiceImpl(pathologyRepository, PathologyMapper.INSTANCE);
    }

    @Test
    public void getAllPathologies(){
        //given
        List<Pathology> pathologies = Arrays.asList(new Pathology(), new Pathology(), new Pathology());
        when(pathologyRepository.findAll()).thenReturn(pathologies);

        //when
        List<PathologyDTO> pathologyDTOS = pathologyService.getAllPathologies();

        //then
        assertEquals(3, pathologyDTOS.size());
    }

    @Test
    public void getPathologyByName(){
        //given
        Pathology pathology = Pathology.builder().id(ID).name(NAME).description(DESCRIPTION).build();
        when(pathologyRepository.findByName(anyString())).thenReturn(pathology);

        //when
        PathologyDTO pathologyDTO = pathologyService.getPathologyByName(NAME);

        //then
        assertEquals(ID, pathologyDTO.getId());
        assertEquals(NAME, pathologyDTO.getName());
        assertEquals(DESCRIPTION, pathologyDTO.getDescription());
    }
}
