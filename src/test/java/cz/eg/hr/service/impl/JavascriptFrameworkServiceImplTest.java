package cz.eg.hr.service.impl;

import cz.eg.hr.model.JavascriptFramework;
import cz.eg.hr.model.dto.JavascriptFrameworkRequestDto;
import cz.eg.hr.model.dto.JavascriptFrameworkResponseDto;
import cz.eg.hr.repository.JavascriptFrameworkRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JavascriptFrameworkServiceImplTest {


    @Mock
    private JavascriptFrameworkRepository repository;

    @InjectMocks
    private JavascriptFrameworkServiceImpl service;

    public JavascriptFrameworkServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnAllJavascriptFrameworks() {
        // Arrange
        JavascriptFramework framework1 = new JavascriptFramework();
        framework1.setId(1L);
        framework1.setName("React");
        framework1.setVersion("17.0.2");
        framework1.setRating(5);
        framework1.setEndOfSupport(LocalDate.of(2025, 12, 31));

        JavascriptFramework framework2 = new JavascriptFramework();
        framework2.setId(2L);
        framework2.setName("Angular");
        framework2.setVersion("12");
        framework2.setRating(4);
        framework2.setEndOfSupport(LocalDate.of(2024, 6, 30));

        List<JavascriptFramework> frameworks = Arrays.asList(framework1, framework2);

        when(repository.findAll()).thenReturn(frameworks);

        // Act
        Iterable<JavascriptFrameworkResponseDto> result = service.findAll();

        // Verify
        verify(repository, times(1)).findAll();

        // Assert
        assertEquals(2, ((List<?>) result).size());
        JavascriptFrameworkResponseDto dto1 = ((List<JavascriptFrameworkResponseDto>) result).get(0);
        assertEquals(1L, dto1.getId());
        assertEquals("React", dto1.getName());
        assertEquals("17.0.2", dto1.getVersion());
        assertEquals(5, dto1.getRating());
        assertEquals(LocalDate.of(2025, 12, 31), dto1.getEndOfSupport());

        JavascriptFrameworkResponseDto dto2 = ((List<JavascriptFrameworkResponseDto>) result).get(1);
        assertEquals(2L, dto2.getId());
        assertEquals("Angular", dto2.getName());
        assertEquals("12", dto2.getVersion());
        assertEquals(4, dto2.getRating());
        assertEquals(LocalDate.of(2024, 6, 30), dto2.getEndOfSupport());

    }


    @Test
    void findById_ShouldReturnFramework_WhenIdExists() {
        // Arrange
        JavascriptFramework framework = new JavascriptFramework();
        framework.setId(1L);
        framework.setName("Vue.js");
        framework.setVersion("3.2");
        framework.setRating(5);
        framework.setEndOfSupport(LocalDate.of(2026, 10, 15));

        when(repository.findById(1L)).thenReturn(Optional.of(framework));

        // Act
        Optional<JavascriptFrameworkResponseDto> result = service.findById(1L);

        // Verify
        verify(repository, times(1)).findById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("Vue.js", result.get().getName());
        assertEquals("3.2", result.get().getVersion());
        assertEquals(5, result.get().getRating());
        assertEquals(LocalDate.of(2026, 10, 15), result.get().getEndOfSupport());
    }

    @Test
    void findById_ShouldReturnEmpty_WhenIdDoesNotExist() {
        // Arrange
        when(repository.findById(999L)).thenReturn(Optional.empty());

        // Act
        Optional<JavascriptFrameworkResponseDto> result = service.findById(999L);

        // Verify
        verify(repository, times(1)).findById(999L);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void save_ShouldReturnSavedFramework() {
        // Arrange
        JavascriptFrameworkRequestDto requestDto = new JavascriptFrameworkRequestDto();
        requestDto.setName("React");
        requestDto.setVersion("18.0");
        requestDto.setRating(5);
        requestDto.setEndOfSupport(LocalDate.of(2026, 12, 31));

        JavascriptFramework framework = new JavascriptFramework();
        framework.setId(1L);
        framework.setName("React");
        framework.setVersion("18.0");
        framework.setRating(5);
        framework.setEndOfSupport(LocalDate.of(2026, 12, 31));

        when(repository.save(any(JavascriptFramework.class))).thenReturn(framework);

        // Act
        JavascriptFrameworkResponseDto result = service.save(requestDto);

        // Verify
        verify(repository, times(1)).save(any(JavascriptFramework.class));

        // Assert
        assertEquals(1L, result.getId());
        assertEquals("React", result.getName());
        assertEquals("18.0", result.getVersion());
        assertEquals(5, result.getRating());
        assertEquals(LocalDate.of(2026, 12, 31), result.getEndOfSupport());
    }

    @Test
    void save_ShouldThrowException_WhenSavingFails() {
        // Arrange
        JavascriptFrameworkRequestDto requestDto = new JavascriptFrameworkRequestDto();
        requestDto.setName(null);  // Simulating invalid data

        when(repository.save(any(JavascriptFramework.class))).thenThrow(new IllegalArgumentException("Invalid Framework Data"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> service.save(requestDto));
        verify(repository, times(1)).save(any(JavascriptFramework.class));
    }

    @Test
    void delete_ShouldDeleteFramework_WhenIdExists() {
        // Arrange
        JavascriptFramework framework = new JavascriptFramework();
        framework.setId(1L);
        framework.setName("Svelte");
        framework.setVersion("3.0");
        framework.setRating(5);
        framework.setEndOfSupport(LocalDate.of(2025, 12, 31));

        when(repository.findById(1L)).thenReturn(Optional.of(framework));

        // Act
        assertDoesNotThrow(() -> service.delete(1L));

        // Verify
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).delete(framework);
    }

    @Test
    void delete_ShouldThrowException_WhenIdDoesNotExist() {
        // Arrange
        when(repository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.delete(999L));

        // Verify
        verify(repository, times(1)).findById(999L);
        verify(repository, never()).delete(any(JavascriptFramework.class));

        // Assert message
        assertEquals("No such framework with id 999", exception.getMessage());
    }


    @Test
    void update_ShouldReturnUpdatedFramework_WhenIdExists() {
        // Arrange
        Long id = 1L;
        JavascriptFrameworkRequestDto requestDto = new JavascriptFrameworkRequestDto();
        requestDto.setName("Updated React");
        requestDto.setVersion("18.2");
        requestDto.setRating(5);
        requestDto.setEndOfSupport(LocalDate.of(2025, 12, 31));

        JavascriptFramework existingFramework = new JavascriptFramework();
        existingFramework.setId(id);
        existingFramework.setName("React");
        existingFramework.setVersion("18.0");
        existingFramework.setRating(5);
        existingFramework.setEndOfSupport(LocalDate.of(2024, 12, 31));

        JavascriptFramework updatedFramework = new JavascriptFramework();
        updatedFramework.setId(id);
        updatedFramework.setName("Updated React");
        updatedFramework.setVersion("18.2");
        updatedFramework.setRating(5);
        updatedFramework.setEndOfSupport(LocalDate.of(2025, 12, 31));

        when(repository.findById(id)).thenReturn(Optional.of(existingFramework));
        when(repository.save(any(JavascriptFramework.class))).thenReturn(updatedFramework);

        // Act
        JavascriptFrameworkResponseDto result = service.update(id, requestDto);

        // Verify
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(any(JavascriptFramework.class));

        // Assert
        assertEquals(id, result.getId());
        assertEquals("Updated React", result.getName());
        assertEquals("18.2", result.getVersion());
        assertEquals(5, result.getRating());
        assertEquals(LocalDate.of(2025, 12, 31), result.getEndOfSupport());
    }

    @Test
    void update_ShouldThrowException_WhenIdDoesNotExist() {
        // Arrange
        Long id = 999L;
        JavascriptFrameworkRequestDto requestDto = new JavascriptFrameworkRequestDto();
        requestDto.setName("Nonexistent");
        requestDto.setVersion("1.0");
        requestDto.setRating(3);
        requestDto.setEndOfSupport(LocalDate.of(2023, 12, 31));

        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.update(id, requestDto));

        // Verify
        verify(repository, times(1)).findById(id);
        verify(repository, never()).save(any(JavascriptFramework.class));

        // Assert message
        assertEquals("No such framework with id " + id, exception.getMessage());
    }
}
