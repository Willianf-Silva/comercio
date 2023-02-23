package br.com.wnfasolutions.comercio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.wnfasolutions.comercio.dto.request.PedidoRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.PedidoResponseDTO;
import br.com.wnfasolutions.comercio.entity.PedidoDO;

@Mapper
public interface PedidoMapper {

	PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);

	PedidoDO toModel(PedidoRequestDTO pedidoRequestDTO);

	@Mapping(target = "situacaoPedido", expression = "java(pedidoDO.getSituacaoPedido().getClass().getAnnotation(javax.persistence.DiscriminatorValue.class).value())")
	@Mapping(target = "orcamento.situacaoOrcamento", expression = "java(orcamentoDO.getSituacaoOrcamento().getClass().getAnnotation(javax.persistence.DiscriminatorValue.class).value())")
	PedidoResponseDTO toResponseDTO(PedidoDO pedidoDO);

}