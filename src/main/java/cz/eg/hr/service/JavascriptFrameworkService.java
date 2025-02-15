package cz.eg.hr.service;

import cz.eg.hr.data.JavascriptFramework;

import java.util.Optional;

public interface JavascriptFrameworkService {
    Iterable<JavascriptFramework> findAll();
    Optional<JavascriptFramework> findById(Long id);
    JavascriptFramework save(JavascriptFramework javascriptFramework);
    void delete(Long id);
    Iterable<JavascriptFramework> findAllByNameContainingIgnoreCase(String name);
}
