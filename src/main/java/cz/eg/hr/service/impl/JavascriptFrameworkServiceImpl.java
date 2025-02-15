package cz.eg.hr.service.impl;

import cz.eg.hr.data.JavascriptFramework;
import cz.eg.hr.repository.JavascriptFrameworkRepository;
import cz.eg.hr.service.JavascriptFrameworkService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JavascriptFrameworkServiceImpl implements JavascriptFrameworkService {
    private final JavascriptFrameworkRepository repository;

    public JavascriptFrameworkServiceImpl(JavascriptFrameworkRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<JavascriptFramework> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<JavascriptFramework> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public JavascriptFramework save(JavascriptFramework javascriptFramework) {
        return repository.save(javascriptFramework);
    }

    @Override
    public void delete(Long id) {
        Optional<JavascriptFramework> javascriptFramework = findById(id);
        if (javascriptFramework.isEmpty()) {
            throw new IllegalArgumentException("No such framework with id " + id);
        }
        repository.delete(javascriptFramework.get());
    }

    @Override
    public Iterable<JavascriptFramework> findAllByNameContainingIgnoreCase(String name) {
        return repository.findAllByNameContainingIgnoreCase(name);
    }
}
