package br.com.wnfasolutions.comercio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.wnfasolutions.comercio.dto.request.ServicoRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.ServicoResponseDTO;
import br.com.wnfasolutions.comercio.entity.ServicoDO;

@Mapper
public interface ServicoMapper {

	ServicoMapper INSTANCE = Mappers.getMapper(ServicoMapper.class);

    ServicoDO toModel(ServicoRequestDTO servicoRequestDTO);
    ServicoDO toModel(ServicoResponseDTO servicoResponseDTO);

    ServicoResponseDTO toResponseDTO(ServicoDO servicoDO);

}