package br.com.wnfasolutions.comercio.resource.swagger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import br.com.wnfasolutions.comercio.dto.request.ClienteRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.ClienteResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Cliente")
public interface ClienteResourceSwagger {

	@ApiOperation("Incluir um novo cliente.")
	public ResponseEntity<ClienteResponseDTO> cadastrarCliente(
			@ApiParam("Informações do clientes que será incluído.") ClienteRequestDTO clienteRequestDTO,
			HttpServletResponse resp) throws Exception;
	
	@ApiOperation("Atualizar um cliente existente.")
	public ResponseEntity<ClienteResponseDTO> atualizarCliente(
			@ApiParam(value = "ID do cliente", example = "01") Long id,
			@ApiParam("Informações do clientes que será incluído.") ClienteRequestDTO clienteRequestDTO) throws Exception;
	
	@ApiOperation("Buscar um cliente através do identificador.")
	public ResponseEntity<ClienteResponseDTO> buscarPorId(
			@ApiParam(value = "ID do cliente", example = "01") Long id) throws Exception;
	
	@ApiOperation("Inativar um cliente através do identificador.")
	public ResponseEntity<ClienteResponseDTO> inativarCliente(
			@ApiParam(value = "ID do cliente", example = "01") Long id) throws Exception;
	
	@ApiOperation("Ativar um cliente através do identificador.")
	public ResponseEntity<ClienteResponseDTO> ativarCliente(
			@ApiParam(value = "ID do cliente", example = "01") Long id) throws Exception;
}
