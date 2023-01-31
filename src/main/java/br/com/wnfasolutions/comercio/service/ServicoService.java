package br.com.wnfasolutions.comercio.service;

import br.com.wnfasolutions.comercio.dto.request.ServicoRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.ServicoResponseDTO;

public interface ServicoService {

	ServicoResponseDTO cadastrarServico(ServicoRequestDTO servicoRequestDTO) throws Exception;

	ServicoResponseDTO atualizarServico(Long id, ServicoRequestDTO servicoRequestDTO) throws Exception;

	ServicoResponseDTO buscarPorId(Long id) throws Exception;

	void inativarServico(Long id) throws Exception;

	void ativarServico(Long id) throws Exception;

}
