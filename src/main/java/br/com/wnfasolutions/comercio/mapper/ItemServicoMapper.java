package br.com.wnfasolutions.comercio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.wnfasolutions.comercio.dto.response.ItemServicoResponseDTO;
import br.com.wnfasolutions.comercio.entity.ItemServicoDO;

@Mapper
public interface ItemServicoMapper {

	ItemServicoMapper INSTANCE = Mappers.getMapper(ItemServicoMapper.class);

	ItemServicoResponseDTO toResponseDTO(ItemServicoDO itemServicoDO);
}