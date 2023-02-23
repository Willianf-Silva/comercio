package br.com.wnfasolutions.comercio.service;

import br.com.wnfasolutions.comercio.dto.request.PedidoRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.PedidoResponseDTO;

public interface PedidoService {

	PedidoResponseDTO cadastrarPedido(PedidoRequestDTO pedidoRequestDTO) throws Exception;

}
