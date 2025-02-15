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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class JavascriptFrameworkServiceImpl implements JavascriptFrameworkService {
    private final JavascriptFrameworkRepository repository;
    private final JavascriptFrameworkMapper mapper = JavascriptFrameworkMapper.INSTANCE;

    public JavascriptFrameworkServiceImpl(JavascriptFrameworkRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<JavascriptFrameworkResponseDto> findAll() {
        Iterable<JavascriptFramework> frameworks =  repository.findAll();
        return mapFrameworksToDto(frameworks);

    }

    @Override
    public Optional<JavascriptFrameworkResponseDto> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    @Override
    public JavascriptFrameworkResponseDto save(JavascriptFrameworkRequestDto requestDto) {
        JavascriptFramework javascriptFramework = mapper.toEntity(requestDto);

        return mapper.toDTO(repository.save(javascriptFramework));
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
        Iterable<JavascriptFramework> frameworks = repository.findAllByNameContainingIgnoreCase(name);
        return mapFrameworksToDto(frameworks);
    }

    @Override
    public JavascriptFrameworkResponseDto update(Long id, JavascriptFrameworkRequestDto requestDto) {
        Optional<JavascriptFramework> javascriptFramework = repository.findById(id);
        if (javascriptFramework.isEmpty()) {
            throw new IllegalArgumentException("No such framework with id " + id);
        }
        JavascriptFramework updatedFramework = mapper.toEntity(requestDto);
        updatedFramework.setId(id);
        return mapper.toDTO(repository.save(updatedFramework));
    }

    private List<JavascriptFrameworkResponseDto> mapFrameworksToDto(Iterable<JavascriptFramework> frameworks) {
        return StreamSupport.stream(frameworks.spliterator(), false)
            .map(mapper::toDTO)
            .collect(Collectors.toList());
    }
}
