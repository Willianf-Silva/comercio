package br.com.wnfasolutions.comercio.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.wnfasolutions.comercio.dto.request.ServicoRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.ServicoResponseDTO;
import br.com.wnfasolutions.comercio.repository.filtro.ServicoFiltro;

public interface ServicoService {

	ServicoResponseDTO cadastrarServico(ServicoRequestDTO servicoRequestDTO) throws Exception;

	ServicoResponseDTO atualizarServico(Long id, ServicoRequestDTO servicoRequestDTO) throws Exception;

	ServicoResponseDTO buscarPorId(Long id) throws Exception;

	void inativarServico(Long id) throws Exception;

	void ativarServico(Long id) throws Exception;

	Page<ServicoResponseDTO> buscarServicos(ServicoFiltro servicoFiltro, Pageable pageable);

}
