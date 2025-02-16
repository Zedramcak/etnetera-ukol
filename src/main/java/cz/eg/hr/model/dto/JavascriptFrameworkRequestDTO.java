package cz.eg.hr.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Schema
public record JavascriptFrameworkRequestDTO (
    @NotBlank
    @NotNull
    @Schema(
        description = "Framework name",
        example = "React"
    )
    String name,
    @NotBlank
    @NotNull
    @Schema(
        description = "Framework version",
        example = "1.0"
    )
    String version,
    @Max(value = 5)
    @Min(value = 1)
    @Schema(
        description = "Framework rating",
        example = "1"
    )
    Integer rating,
    @Schema(
        description = "Frameworks end of support",
        example = "2025-12-31"
    )
    LocalDate endOfSupport
){
}
