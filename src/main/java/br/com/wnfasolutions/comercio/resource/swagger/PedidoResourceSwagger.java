package br.com.wnfasolutions.comercio.resource.swagger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.com.wnfasolutions.comercio.dto.request.PedidoRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.PedidoResponseDTO;
import br.com.wnfasolutions.comercio.repository.filtro.PedidoFiltro;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Pedidos")
public interface PedidoResourceSwagger {

	@ApiOperation("Incluir um novo pedido.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<PedidoResponseDTO> cadastrarOrcamento(
			@ApiParam("Informações do pedido que será incluído.") PedidoRequestDTO pedidoRequestDTO, 
			HttpServletResponse resp) throws Exception;
	
	@ApiImplicitParams({
        @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                value = "Quantidade de registros", defaultValue = "1"),
        @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                value = "Pagina a ser carregada", defaultValue = "0"),
        @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                value = "Ordenação dos registros")
	})
	@ApiOperation("Buscar pedidos com filtro")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<Page<PedidoResponseDTO>> buscarPedidos(
			@ApiParam("Opções de filtro para consulta") PedidoFiltro pedidoFiltro, 
			@ApiIgnore Pageable pageable) throws Exception;
	
	@ApiOperation("Altera a situação do pedido para Aguardando Envio para Produção.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<?> enviarPedidoProducao(
			@ApiParam(value = "ID do pedido", example = "01") Long id) throws Exception;
	
	@ApiOperation("Altera a situação do pedido para Aguardando Retirada Cliente.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<?> pedidoAguardandoRetirada(
			@ApiParam(value = "ID do pedido", example = "01") Long id) throws Exception;
	
	@ApiOperation("Altera a situação do pedido para Entregue ao Cliente.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<?> pedidoEntregue(
			@ApiParam(value = "ID do pedido", example = "01") Long id) throws Exception;
}
