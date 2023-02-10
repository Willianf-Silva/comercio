package br.com.wnfasolutions.comercio.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.wnfasolutions.comercio.dto.request.PrestadorRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.PrestadorResponseDTO;
import br.com.wnfasolutions.comercio.repository.filtro.PrestadorFiltro;

public interface PrestadorService {

	PrestadorResponseDTO cadastrarPrestador(PrestadorRequestDTO prestadorRequestDTO) throws Exception;

	PrestadorResponseDTO atualizarPrestador(Long id, PrestadorRequestDTO prestadorRequestDTO) throws Exception;

	PrestadorResponseDTO buscarPorId(Long id) throws Exception;

	void inativarPrestador(Long id) throws Exception;

	void ativarPrestador(Long id) throws Exception;

	Page<PrestadorResponseDTO> buscarPrestadores(PrestadorFiltro prestadorFiltro, Pageable pageable);

}
