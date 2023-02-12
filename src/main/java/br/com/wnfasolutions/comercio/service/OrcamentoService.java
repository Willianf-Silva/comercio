package br.com.wnfasolutions.comercio.service;

import br.com.wnfasolutions.comercio.dto.request.OrcamentoRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.OrcamentoResponseDTO;

public interface OrcamentoService {

	OrcamentoResponseDTO cadastrarOrcamento(OrcamentoRequestDTO orcamentoRequestDTO) throws Exception;

	OrcamentoResponseDTO atualizarOrcamento(Long id, OrcamentoRequestDTO orcamentoRequestDTO) throws Exception;

}
