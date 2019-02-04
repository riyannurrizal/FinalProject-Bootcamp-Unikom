package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.CategoryColor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CategoryColorMapperRequestNew extends ObjectMapper<CategoryColor, CategoryColorDTO.CategoryColorRequestNewDTO> {

    CategoryColorMapperRequestNew converter = Mappers.getMapper(CategoryColorMapperRequestNew.class);

}
