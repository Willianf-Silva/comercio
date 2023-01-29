package br.com.wnfasolutions.comercio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.wnfasolutions.comercio.dto.request.ClienteRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.ClienteResponseDTO;
import br.com.wnfasolutions.comercio.entity.ClienteDO;

@Mapper
public interface ClienteMapper {

	ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

	ClienteDO toModel(ClienteRequestDTO clientRequestDTO);

	ClienteResponseDTO toResponseDTO(ClienteDO clientDO);
}