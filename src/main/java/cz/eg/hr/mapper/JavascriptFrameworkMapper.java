package cz.eg.hr.mapper;

import cz.eg.hr.model.JavascriptFramework;
import cz.eg.hr.model.dto.JavascriptFrameworkRequestDTO;
import cz.eg.hr.model.dto.JavascriptFrameworkResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JavascriptFrameworkMapper {
    JavascriptFrameworkMapper INSTANCE = Mappers.getMapper(JavascriptFrameworkMapper.class);

    JavascriptFramework toEntity(JavascriptFrameworkRequestDTO dto);

    JavascriptFrameworkResponseDTO toDTO(JavascriptFramework entity);
}
