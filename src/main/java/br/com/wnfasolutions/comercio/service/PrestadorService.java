package br.com.wnfasolutions.comercio.service;

import br.com.wnfasolutions.comercio.dto.request.PrestadorRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.PrestadorResponseDTO;

public interface PrestadorService {

	PrestadorResponseDTO cadastrarPrestador(PrestadorRequestDTO prestadorRequestDTO) throws Exception;

	PrestadorResponseDTO atualizarPrestador(Long id, PrestadorRequestDTO prestadorRequestDTO) throws Exception;

	PrestadorResponseDTO buscarPorId(Long id) throws Exception;

	void inativarPrestador(Long id) throws Exception;

	void ativarPrestador(Long id) throws Exception;

}
