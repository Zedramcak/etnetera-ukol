package cz.eg.hr.service;

import cz.eg.hr.model.dto.JavascriptFrameworkRequestDto;
import cz.eg.hr.model.dto.JavascriptFrameworkResponseDto;

import java.util.Optional;

public interface JavascriptFrameworkService {
    Iterable<JavascriptFrameworkResponseDto> findAll();
    Optional<JavascriptFrameworkResponseDto> findById(Long id);
    JavascriptFrameworkResponseDto save(JavascriptFrameworkRequestDto requestDto);
    void delete(Long id);
    JavascriptFrameworkResponseDto update(Long id, JavascriptFrameworkRequestDto requestDto);
    Iterable<JavascriptFrameworkResponseDto> findAllByNameContainingIgnoreCase(String name);
}
