package br.com.wnfasolutions.comercio.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.wnfasolutions.comercio.dto.request.ClienteRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.ClienteResponseDTO;
import br.com.wnfasolutions.comercio.repository.filtro.ClienteFiltro;

public interface ClienteService {

	ClienteResponseDTO cadastrarCliente(ClienteRequestDTO clienteRequestDTO) throws Exception;

	ClienteResponseDTO atualizarCliente(Long id, ClienteRequestDTO clienteRequestDTO) throws Exception;

	ClienteResponseDTO buscarPorId(Long id) throws Exception;

	void inativarCliente(Long id) throws Exception;

	void ativarCliente(Long id) throws Exception;

	Page<ClienteResponseDTO> buscarClientes(ClienteFiltro clienteFiltro, Pageable pageable);

}
