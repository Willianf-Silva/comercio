package br.com.wnfasolutions.comercio.resource.swagger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.com.wnfasolutions.comercio.dto.request.PrestadorRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.PrestadorResponseDTO;
import br.com.wnfasolutions.comercio.repository.filtro.PrestadorFiltro;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Prestadores")
public interface PrestadorResourceSwagger {

	@ApiOperation("Incluir um novo prestador.")
	public ResponseEntity<PrestadorResponseDTO> cadastrarPrestador(
			@ApiParam("Informações do prestadors que será incluído.") PrestadorRequestDTO prestadorRequestDTO,
			HttpServletResponse resp) throws Exception;
	
	@ApiOperation("Atualizar um prestador existente.")
	public ResponseEntity<PrestadorResponseDTO> atualizarPrestador(
			@ApiParam(value = "ID do prestador", example = "01") Long id,
			@ApiParam("Informações do prestadors que será incluído.") PrestadorRequestDTO prestadorRequestDTO) throws Exception;
	
	@ApiOperation("Buscar um prestador através do identificador.")
	public ResponseEntity<PrestadorResponseDTO> buscarPorId(
			@ApiParam(value = "ID do prestador", example = "01") Long id) throws Exception;
	
	@ApiImplicitParams({
        @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                value = "Quantidade de registros", defaultValue = "1"),
        @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                value = "Pagina a ser carregada", defaultValue = "0"),
        @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                value = "Ordenação dos registros")
	})
	@ApiOperation("Buscar prestadores com filtro")
	public ResponseEntity<Page<PrestadorResponseDTO>> buscarPrestadores(
			@ApiParam("Opções de filtro para consulta") PrestadorFiltro prestadorFiltro, 
			@ApiIgnore Pageable pageable);
	
	@ApiOperation("Inativar um prestador através do identificador.")
	public ResponseEntity<PrestadorResponseDTO> inativarPrestador(
			@ApiParam(value = "ID do prestador", example = "01") Long id) throws Exception;
	
	@ApiOperation("Ativar um prestador através do identificador.")
	public ResponseEntity<PrestadorResponseDTO> ativarPrestador(
			@ApiParam(value = "ID do prestador", example = "01") Long id) throws Exception;
}
