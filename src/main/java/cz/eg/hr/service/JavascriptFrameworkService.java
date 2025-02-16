package cz.eg.hr.service;

import cz.eg.hr.model.dto.JavascriptFrameworkRequestDTO;
import cz.eg.hr.model.dto.JavascriptFrameworkResponseDTO;

public interface JavascriptFrameworkService {
    Iterable<JavascriptFrameworkResponseDTO> findAll();
    JavascriptFrameworkResponseDTO findById(Long id);
    JavascriptFrameworkResponseDTO save(JavascriptFrameworkRequestDTO requestDto);
    void delete(Long id);
    JavascriptFrameworkResponseDTO update(Long id, JavascriptFrameworkRequestDTO requestDto);
    Iterable<JavascriptFrameworkResponseDTO> findAllByNameOrVersion(String search);
}
