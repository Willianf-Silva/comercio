package br.com.wnfasolutions.comercio.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.wnfasolutions.comercio.dto.request.PedidoRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.PedidoResponseDTO;
import br.com.wnfasolutions.comercio.repository.filtro.PedidoFiltro;

public interface PedidoService {

	PedidoResponseDTO cadastrarPedido(PedidoRequestDTO pedidoRequestDTO) throws Exception;

	PedidoResponseDTO enviarPedidoProducao(Long id) throws Exception;

	PedidoResponseDTO pedidoAguardandoRetirada(Long id) throws Exception;

	PedidoResponseDTO pedidoEntregue(Long id) throws Exception;

	Page<PedidoResponseDTO> buscarPedidos(PedidoFiltro pedidoFiltro, Pageable pageable) throws Exception;

}
