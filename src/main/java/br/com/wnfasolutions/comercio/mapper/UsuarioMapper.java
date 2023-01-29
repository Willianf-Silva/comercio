package br.com.wnfasolutions.comercio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.wnfasolutions.comercio.dto.request.UsuarioRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.UsuarioResponseDTO;
import br.com.wnfasolutions.comercio.entity.UsuarioDO;

@Mapper
public interface UsuarioMapper {

	UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "situacao", ignore = true)
	UsuarioDO toModel(UsuarioRequestDTO veterinarianRequestDTO);

	UsuarioResponseDTO toResponseDTO(UsuarioDO veterinarianDO);
}