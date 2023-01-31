package br.com.wnfasolutions.comercio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.wnfasolutions.comercio.dto.request.PrestadorRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.PrestadorResponseDTO;
import br.com.wnfasolutions.comercio.entity.PrestadorDO;

@Mapper
public interface PrestadorMapper {

	PrestadorMapper INSTANCE = Mappers.getMapper(PrestadorMapper.class);

	PrestadorDO toModel(PrestadorRequestDTO prestadorRequestDTO);

	PrestadorResponseDTO toResponseDTO(PrestadorDO prestadorDO);
}