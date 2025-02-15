package cz.eg.hr.controller;


import cz.eg.hr.model.dto.JavascriptFrameworkRequestDTO;
import cz.eg.hr.model.dto.JavascriptFrameworkResponseDTO;
import cz.eg.hr.service.JavascriptFrameworkService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class JavascriptFrameworkControllerTest {
    @Mock
    private JavascriptFrameworkService service;

    @InjectMocks
    private JavascriptFrameworkController controller;

    public JavascriptFrameworkControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_findsFramework() {
        JavascriptFrameworkResponseDTO dto = new JavascriptFrameworkResponseDTO();

        when(service.findById(anyLong())).thenReturn(Optional.of(dto));

        ResponseEntity<JavascriptFrameworkResponseDTO> response = controller.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void findById_nothingFound() {
        when(service.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<JavascriptFrameworkResponseDTO> response = controller.findById(1L);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void save_savesFramework() {
        JavascriptFrameworkRequestDTO dto = new JavascriptFrameworkRequestDTO();
        dto.setName("Vue.js");
        dto.setVersion("3.2");

        JavascriptFrameworkResponseDTO response = new JavascriptFrameworkResponseDTO();
        response.setId(1L);
        response.setName("Vue.js");
        response.setVersion("3.2");

        when(service.save(dto)).thenReturn(response);

        ResponseEntity<JavascriptFrameworkResponseDTO> responseEntity = controller.save(dto);

        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(response, responseEntity.getBody());
    }

}
