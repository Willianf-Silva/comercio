package br.com.wnfasolutions.comercio.resource.swagger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import br.com.wnfasolutions.comercio.dto.request.PedidoRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.PedidoResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Pedidos")
public interface PedidoResourceSwagger {

	@ApiOperation("Incluir um novo pedido.")
	public ResponseEntity<PedidoResponseDTO> cadastrarOrcamento(
			@ApiParam("Informações do pedido que será incluído.") PedidoRequestDTO pedidoRequestDTO, 
			HttpServletResponse resp) throws Exception;
}
