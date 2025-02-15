package cz.eg.hr.service.impl;

import cz.eg.hr.mapper.JavascriptFrameworkMapper;
import cz.eg.hr.model.JavascriptFramework;
import cz.eg.hr.model.dto.JavascriptFrameworkRequestDto;
import cz.eg.hr.model.dto.JavascriptFrameworkResponseDto;
import cz.eg.hr.repository.JavascriptFrameworkRepository;
import cz.eg.hr.service.JavascriptFrameworkService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JavascriptFrameworkServiceImpl implements JavascriptFrameworkService {
    private final JavascriptFrameworkRepository repository;

    public JavascriptFrameworkServiceImpl(JavascriptFrameworkRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<JavascriptFrameworkResponseDto> findAll() {
        Iterable<JavascriptFramework> frameworks =  repository.findAll();
        frameworks.forEach(System.out::println);
        return List.of();

    }

    @Override
    public Optional<JavascriptFrameworkResponseDto> findById(Long id) {
        return repository.findById(id).map(JavascriptFrameworkMapper.INSTANCE::toDTO);
    }

    @Override
    public JavascriptFrameworkResponseDto save(JavascriptFrameworkRequestDto requestDto) {
        JavascriptFramework javascriptFramework = JavascriptFrameworkMapper.INSTANCE.toEntity(requestDto);

        return JavascriptFrameworkMapper.INSTANCE.toDTO(repository.save(javascriptFramework));
    }

    @Override
    public void delete(Long id) {
        Optional<JavascriptFramework> javascriptFramework = repository.findById(id);
        if (javascriptFramework.isEmpty()) {
            throw new IllegalArgumentException("No such framework with id " + id);
        }
        repository.delete(javascriptFramework.get());
    }

    @Override
    public Iterable<JavascriptFrameworkResponseDto> findAllByNameContainingIgnoreCase(String name) {
        return List.of();
    }
}
