package cz.eg.hr.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Schema
@Data
public class JavascriptFrameworkResponseDTO {
    @Schema(description = "Framework ID", example = "1")
    private Long id;
    @Schema(description = "Framework name")
    private String name;
    @Schema(description = "Framework version")
    private String version;
    @Schema(description = "Framework rating",  example = "1")
    private Integer rating;
    @Schema(description = "Frameworks end of support")
    private LocalDate endOfSupport;
}
