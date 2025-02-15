package cz.eg.hr.controller;

import cz.eg.hr.model.dto.JavascriptFrameworkRequestDto;
import cz.eg.hr.model.dto.JavascriptFrameworkResponseDto;
import cz.eg.hr.service.JavascriptFrameworkService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(ApiConstants.API_FRAMEWORKS)
@RequiredArgsConstructor
@Tag(name = "Javascript Frameworks", description = "CRUD operations for Javascript Frameworks")
public class JavascriptFrameworkController {

    private final JavascriptFrameworkService service;

    @GetMapping
    @ApiResponse(
        responseCode = "200",
        description = "list of frameworks",
        content = @Content(
            array = @ArraySchema(
                schema = @Schema(implementation = JavascriptFrameworkResponseDto.class)
            )
        )
    )
    public ResponseEntity<Iterable<JavascriptFrameworkResponseDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(ApiConstants.API_FRAMEWORKS_ID)
    @ApiResponse(
        responseCode = "200",
        description = "found framework",
        content = @Content(schema = @Schema(implementation = JavascriptFrameworkResponseDto.class))
    )
    @ApiResponse(responseCode = "404", description = "framework not found" , content = @Content)
    public ResponseEntity<JavascriptFrameworkResponseDto> findById(
        @Parameter(description = "Framework ID", example = "1") @PathVariable Long id
    ) {
        Optional<JavascriptFrameworkResponseDto> framework = service.findById(id);
        return framework.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ApiResponse(
        responseCode = "201",
        description = "framework created",
        content = @Content(schema = @Schema(implementation = JavascriptFrameworkResponseDto.class))
    )
    @ApiResponse(responseCode = "400", description = "Invalid arguments" , content = @Content(schema = @Schema(implementation = MethodArgumentNotValidException.class)))
    @ApiResponse(responseCode = "409", description = "framework already exists" , content = @Content)
    @ApiResponse(responseCode = "500", description = "internal server error" , content = @Content)
    public ResponseEntity<JavascriptFrameworkResponseDto> save(@RequestBody @Valid JavascriptFrameworkRequestDto javascriptFrameworkRequest
    ) {
        JavascriptFrameworkResponseDto response = service.save(javascriptFrameworkRequest);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping(ApiConstants.API_FRAMEWORKS_SEARCH)
    @ApiResponse(
        responseCode = "200",
        description = "found frameworks",
        content = @Content(
            array = @ArraySchema(
                schema = @Schema(implementation = JavascriptFrameworkResponseDto.class)
            )
        )
    )
    public ResponseEntity<Iterable<JavascriptFrameworkResponseDto>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(service.findAllByNameContainingIgnoreCase(name));
    }

    @DeleteMapping(ApiConstants.API_FRAMEWORKS_DELETE + ApiConstants.API_FRAMEWORKS_ID)
    @ApiResponse(responseCode = "204", description = "framework deleted")
    @ApiResponse(responseCode = "404", description = "framework not found" , content = @Content)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(ApiConstants.API_FRAMEWORKS_ID)
    @ApiResponse(
        responseCode = "200",
        description = "updated framework",
        content = @Content(schema = @Schema(implementation = JavascriptFrameworkResponseDto.class))
    )
    @ApiResponse(responseCode = "404", description = "framework not found" , content = @Content)
    public ResponseEntity<JavascriptFrameworkResponseDto> update(@PathVariable Long id, @RequestBody @Valid JavascriptFrameworkRequestDto javascriptFrameworkRequest) {
        return ResponseEntity.ok(service.update(id, javascriptFrameworkRequest));
    }

}
