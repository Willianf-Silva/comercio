package br.com.wnfasolutions.comercio.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.wnfasolutions.comercio.dto.request.OrcamentoRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.OrcamentoResponseDTO;
import br.com.wnfasolutions.comercio.entity.OrcamentoDO;
import br.com.wnfasolutions.comercio.repository.filtro.OrcamentoFiltro;

public interface OrcamentoService {

	OrcamentoResponseDTO cadastrarOrcamento(OrcamentoRequestDTO orcamentoRequestDTO) throws Exception;

	OrcamentoResponseDTO atualizarOrcamento(Long id, OrcamentoRequestDTO orcamentoRequestDTO) throws Exception;

	Page<OrcamentoResponseDTO> buscarOrcamentos(OrcamentoFiltro orcamentoFiltro, Pageable pageable);

	OrcamentoResponseDTO reprovarOrcamento(Long id) throws Exception;

	OrcamentoDO buscarOrcamentoEmAnaliseById(Long idOrcamento) throws Exception;

	OrcamentoResponseDTO aprovarOrcamento(Long id) throws Exception;

}
