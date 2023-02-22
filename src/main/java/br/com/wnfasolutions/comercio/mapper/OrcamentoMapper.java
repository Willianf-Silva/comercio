package br.com.wnfasolutions.comercio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.wnfasolutions.comercio.dto.request.OrcamentoRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.OrcamentoResponseDTO;
import br.com.wnfasolutions.comercio.entity.OrcamentoDO;

@Mapper
public interface OrcamentoMapper {

	OrcamentoMapper INSTANCE = Mappers.getMapper(OrcamentoMapper.class);

	OrcamentoDO toModel(OrcamentoRequestDTO orcamentoRequestDTO);

	@Mapping(target = "situacaoOrcamento", expression = "java(orcamentoDO.getSituacaoOrcamento().getClass().getAnnotation(javax.persistence.DiscriminatorValue.class).value())")
	OrcamentoResponseDTO toResponseDTO(OrcamentoDO orcamentoDO);

}