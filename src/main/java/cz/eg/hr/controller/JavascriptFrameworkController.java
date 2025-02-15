package cz.eg.hr.controller;

import cz.eg.hr.data.JavascriptFramework;
import cz.eg.hr.service.JavascriptFrameworkService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(ApiConstants.API_FRAMEWORKS)
@RequiredArgsConstructor
@Tag(name = "Javascript Frameworks", description = "CRUD operations for Javascript Frameworks")
public class JavascriptFrameworkController {

    private final JavascriptFrameworkService service;

    @GetMapping
    public ResponseEntity<Iterable<JavascriptFramework>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(ApiConstants.API_FRAMEWORKS_ID)
    public ResponseEntity<JavascriptFramework> findById(
        @Parameter(description = "Framework ID", example = "1") @PathVariable Long id
    ) {
        Optional<JavascriptFramework> framework = service.findById(id);
        return framework.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<JavascriptFramework> save(@RequestBody JavascriptFramework javascriptFramework) {
        return ResponseEntity.ok(service.save(javascriptFramework));
    }

    @GetMapping(ApiConstants.API_FRAMEWORKS_SEARCH)
    public ResponseEntity<Iterable<JavascriptFramework>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(service.findAllByNameContainingIgnoreCase(name));
    }

    @DeleteMapping(ApiConstants.API_FRAMEWORKS_DELETE + ApiConstants.API_FRAMEWORKS_ID)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }



}
