package br.com.wnfasolutions.comercio.resource.swagger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.com.wnfasolutions.comercio.dto.request.ClienteRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.ClienteResponseDTO;
import br.com.wnfasolutions.comercio.repository.filtro.ClienteFiltro;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

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
	
	@ApiImplicitParams({
        @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                value = "Quantidade de registros", defaultValue = "1"),
        @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                value = "Pagina a ser carregada", defaultValue = "0"),
        @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                value = "Ordenação dos registros")
	})
	@ApiOperation("Buscar clientes com filtro")
	public ResponseEntity<Page<ClienteResponseDTO>> buscarClientes(
			@ApiParam("Opções de filtro para consulta") ClienteFiltro clienteFiltro, 
			@ApiIgnore Pageable pageable);
	
	@ApiOperation("Inativar um cliente através do identificador.")
	public ResponseEntity<ClienteResponseDTO> inativarCliente(
			@ApiParam(value = "ID do cliente", example = "01") Long id) throws Exception;
	
	@ApiOperation("Ativar um cliente através do identificador.")
	public ResponseEntity<ClienteResponseDTO> ativarCliente(
			@ApiParam(value = "ID do cliente", example = "01") Long id) throws Exception;
}
