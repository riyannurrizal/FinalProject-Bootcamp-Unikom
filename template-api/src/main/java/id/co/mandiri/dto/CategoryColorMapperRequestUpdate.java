package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.CategoryColor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CategoryColorMapperRequestUpdate extends ObjectMapper<CategoryColor, CategoryColorDTO.CategoryColorRequestUpdateDTO> {

    CategoryColorMapperRequestUpdate converter = Mappers.getMapper(CategoryColorMapperRequestUpdate.class);

}
