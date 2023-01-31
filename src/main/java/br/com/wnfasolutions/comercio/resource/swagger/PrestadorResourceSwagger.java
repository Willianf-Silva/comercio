package br.com.wnfasolutions.comercio.resource.swagger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import br.com.wnfasolutions.comercio.dto.request.PrestadorRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.PrestadorResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
	
	@ApiOperation("Inativar um prestador através do identificador.")
	public ResponseEntity<PrestadorResponseDTO> inativarPrestador(
			@ApiParam(value = "ID do prestador", example = "01") Long id) throws Exception;
	
	@ApiOperation("Ativar um prestador através do identificador.")
	public ResponseEntity<PrestadorResponseDTO> ativarPrestador(
			@ApiParam(value = "ID do prestador", example = "01") Long id) throws Exception;
}
