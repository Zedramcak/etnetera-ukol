package cz.eg.hr.controller;

import cz.eg.hr.model.dto.JavascriptFrameworkRequestDTO;
import cz.eg.hr.model.dto.JavascriptFrameworkResponseDTO;
import cz.eg.hr.service.JavascriptFrameworkService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
                schema = @Schema(implementation = JavascriptFrameworkResponseDTO.class)
            )
        )
    )
    public ResponseEntity<Iterable<JavascriptFrameworkResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(ApiConstants.API_FRAMEWORKS_ID)
    @ApiResponse(
        responseCode = "200",
        description = "found framework",
        content = @Content(schema = @Schema(implementation = JavascriptFrameworkResponseDTO.class))
    )
    @ApiResponse(responseCode = "404", description = "framework not found" , content = @Content)
    public ResponseEntity<JavascriptFrameworkResponseDTO> findById(
        @Parameter(description = "Framework ID", example = "1") @PathVariable Long id
    ) {
        JavascriptFrameworkResponseDTO framework = service.findById(id);
        return ResponseEntity.ok(framework);
    }

    @PostMapping
    @ApiResponse(
        responseCode = "201",
        description = "framework created",
        content = @Content(schema = @Schema(implementation = JavascriptFrameworkResponseDTO.class))
    )
    @ApiResponse(responseCode = "400", description = "Invalid arguments" , content = @Content)
    @ApiResponse(responseCode = "409", description = "framework already exists" , content = @Content)
    public ResponseEntity<JavascriptFrameworkResponseDTO> save(@RequestBody @Valid JavascriptFrameworkRequestDTO javascriptFrameworkRequest
    ) {
        JavascriptFrameworkResponseDTO response = service.save(javascriptFrameworkRequest);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping(ApiConstants.API_FRAMEWORKS_SEARCH)
    @ApiResponse(
        responseCode = "200",
        description = "found frameworks",
        content = @Content(
            array = @ArraySchema(
                schema = @Schema(implementation = JavascriptFrameworkResponseDTO.class)
            )
        )
    )
    public ResponseEntity<Iterable<JavascriptFrameworkResponseDTO>> search(@RequestParam String search) {
        return ResponseEntity.ok(service.findAllByNameOrVersion(search));
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
        content = @Content(schema = @Schema(implementation = JavascriptFrameworkResponseDTO.class))
    )
    @ApiResponse(responseCode = "404", description = "framework not found" , content = @Content)
    @ApiResponse(responseCode = "409", description = "framework with this name and version already exists" , content = @Content)
    public ResponseEntity<JavascriptFrameworkResponseDTO> update(
        @Parameter(description = "Framework ID", example = "1") @PathVariable Long id,
        @RequestBody @Valid JavascriptFrameworkRequestDTO javascriptFrameworkRequest
    ) {
        return ResponseEntity.ok(service.update(id, javascriptFrameworkRequest));
    }

}
