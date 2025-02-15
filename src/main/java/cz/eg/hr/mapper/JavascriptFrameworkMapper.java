package cz.eg.hr.mapper;

import cz.eg.hr.model.JavascriptFramework;
import cz.eg.hr.model.dto.JavascriptFrameworkRequestDto;
import cz.eg.hr.model.dto.JavascriptFrameworkResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JavascriptFrameworkMapper {
    JavascriptFrameworkMapper INSTANCE = Mappers.getMapper(JavascriptFrameworkMapper.class);

    JavascriptFramework toEntity(JavascriptFrameworkRequestDto dto);

    JavascriptFrameworkResponseDto toDTO(JavascriptFramework entity);
}
