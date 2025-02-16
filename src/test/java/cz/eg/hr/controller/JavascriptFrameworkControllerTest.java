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
        JavascriptFrameworkResponseDTO dto = new JavascriptFrameworkResponseDTO(
            null,
            null,
            null,
            null,
            null
        );

        when(service.findById(anyLong())).thenReturn(dto);

        ResponseEntity<JavascriptFrameworkResponseDTO> response = controller.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void save_savesFramework() {
        JavascriptFrameworkRequestDTO dto = new JavascriptFrameworkRequestDTO(
            "Vue.js",
            "3.2",
            null,
            null
        );

        JavascriptFrameworkResponseDTO response = new JavascriptFrameworkResponseDTO(
            1L,
            "Vue.js",
            "3.2",
            null,
            null
        );

        when(service.save(dto)).thenReturn(response);

        ResponseEntity<JavascriptFrameworkResponseDTO> responseEntity = controller.save(dto);

        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(response, responseEntity.getBody());
    }

}
