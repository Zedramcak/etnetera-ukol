package cz.eg.hr.repository;

import cz.eg.hr.model.JavascriptFramework;
import org.springframework.data.repository.CrudRepository;

public interface JavascriptFrameworkRepository extends CrudRepository<JavascriptFramework, Long> {
    Iterable<JavascriptFramework> findAllByNameContainingIgnoreCaseOrVersionContainingIgnoreCase
        (String name, String version);
}

