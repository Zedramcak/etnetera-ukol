package cz.eg.hr.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema
public record JavascriptFrameworkResponseDTO (
    @Schema(description = "Framework ID", example = "1")
    Long id,
    @Schema(description = "Framework name")
    String name,
    @Schema(description = "Framework version")
    String version,
    @Schema(description = "Framework rating",  example = "1")
    Integer rating,
    @Schema(description = "Frameworks end of support")
    LocalDate endOfSupport
){
}
