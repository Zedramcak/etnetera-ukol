package cz.eg.hr.service.impl;

import cz.eg.hr.mapper.JavascriptFrameworkMapper;
import cz.eg.hr.model.JavascriptFramework;
import cz.eg.hr.model.dto.JavascriptFrameworkRequestDTO;
import cz.eg.hr.model.dto.JavascriptFrameworkResponseDTO;
import cz.eg.hr.repository.JavascriptFrameworkRepository;
import cz.eg.hr.service.JavascriptFrameworkService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
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
    public Iterable<JavascriptFrameworkResponseDTO> findAll() {
        Iterable<JavascriptFramework> frameworks =  repository.findAll();
        return mapFrameworksToDto(frameworks);

    }

    @Override
    public JavascriptFrameworkResponseDTO findById(Long id) {
        Optional<JavascriptFramework> framework = repository.findById(id);
        if (framework.isEmpty()) {
            throw new NoSuchElementException("No such framework with id " + id);
        }
        return mapper.toDTO(framework.get());
    }

    @Override
    public JavascriptFrameworkResponseDTO save(JavascriptFrameworkRequestDTO requestDto) {
        LocalDate now = LocalDate.now();
        JavascriptFramework javascriptFramework = mapper.toEntity(requestDto);
        javascriptFramework.setCreationDate(now);
        javascriptFramework.setLastUpdate(now);
        return mapper.toDTO(repository.save(javascriptFramework));
    }

    @Override
    public void delete(Long id) {
        Optional<JavascriptFramework> javascriptFramework = repository.findById(id);
        if (javascriptFramework.isEmpty()) {
            throw new NoSuchElementException("No such framework with id " + id);
        }
        repository.delete(javascriptFramework.get());
    }

    @Override
    public Iterable<JavascriptFrameworkResponseDTO> findAllByNameOrVersion(String search) {
        Iterable<JavascriptFramework> frameworks = repository.findAllByNameContainingIgnoreCaseOrVersionContainingIgnoreCase(search, search);
        return mapFrameworksToDto(frameworks);
    }

    @Override
    public JavascriptFrameworkResponseDTO update(Long id, JavascriptFrameworkRequestDTO requestDto) {
        Optional<JavascriptFramework> javascriptFramework = repository.findById(id);
        if (javascriptFramework.isEmpty()) {
            throw new NoSuchElementException("No such framework with id " + id);
        }
        JavascriptFramework updatedFramework = mapper.toEntity(requestDto);
        updatedFramework.setId(id);
        updatedFramework.setLastUpdate(LocalDate.now());
        return mapper.toDTO(repository.save(updatedFramework));
    }

    private List<JavascriptFrameworkResponseDTO> mapFrameworksToDto(Iterable<JavascriptFramework> frameworks) {
        return StreamSupport.stream(frameworks.spliterator(), false)
            .map(mapper::toDTO)
            .collect(Collectors.toList());
    }
}
