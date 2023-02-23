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
	
	@ApiOperation("Altera a situação do pedido para Aguardando Envio para Produção.")
	public ResponseEntity<?> enviarPedidoProducao(
			@ApiParam(value = "ID do pedido", example = "01") Long id) throws Exception;
	
	@ApiOperation("Altera a situação do pedido para Aguardando Retirada Cliente.")
	public ResponseEntity<?> pedidoAguardandoRetirada(
			@ApiParam(value = "ID do pedido", example = "01") Long id) throws Exception;
	
	@ApiOperation("Altera a situação do pedido para Entregue ao Cliente.")
	public ResponseEntity<?> pedidoEntregue(
			@ApiParam(value = "ID do pedido", example = "01") Long id) throws Exception;
}
