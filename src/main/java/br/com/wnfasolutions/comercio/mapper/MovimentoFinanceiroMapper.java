package br.com.wnfasolutions.comercio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.wnfasolutions.comercio.dto.request.MovimentoFinanceiroRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.MovimentoFinanceiroResponseDTO;
import br.com.wnfasolutions.comercio.entity.MovimentoFinanceiroDO;

@Mapper
public interface MovimentoFinanceiroMapper {

	MovimentoFinanceiroMapper INSTANCE = Mappers.getMapper(MovimentoFinanceiroMapper.class);

	MovimentoFinanceiroDO toModel(MovimentoFinanceiroRequestDTO movimentoFinanceiroRequestDTO);

	MovimentoFinanceiroResponseDTO toResponseDTO(MovimentoFinanceiroDO movimentoFinanceiroDO);
}