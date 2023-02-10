package br.com.wnfasolutions.comercio.resource.swagger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.com.wnfasolutions.comercio.dto.request.UsuarioRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.UsuarioResponseDTO;
import br.com.wnfasolutions.comercio.repository.filtro.UsuarioFiltro;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Usuario")
public interface UsuarioResourceSwagger {

	@ApiOperation("Incluir um novo usuário.")
	public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(
			@ApiParam("Informações do usuários que será incluído.") UsuarioRequestDTO usuarioRequestDTO,
			HttpServletResponse resp) throws Exception;
	
	@ApiOperation("Atualizar um usuário existente.")
	public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(
			@ApiParam(value = "ID do usuário", example = "01") Long id,
			@ApiParam("Informações do usuários que será incluído.") UsuarioRequestDTO usuarioRequestDTO) throws Exception;
	
	@ApiOperation("Buscar um usuário através do identificador.")
	public ResponseEntity<UsuarioResponseDTO> buscarPorId(
			@ApiParam(value = "ID do usuário", example = "01") Long id) throws Exception;
	
	@ApiImplicitParams({
        @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                value = "Quantidade de registros", defaultValue = "1"),
        @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                value = "Pagina a ser carregada", defaultValue = "0"),
        @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                value = "Ordenação dos registros")
	})
	@ApiOperation("Buscar usuarios com filtro")
	public ResponseEntity<Page<UsuarioResponseDTO>> buscarUsuarios(
			@ApiParam("Opções de filtro para consulta") UsuarioFiltro usuarioFiltro, 
			@ApiIgnore Pageable pageable);
	
	@ApiOperation("Inativar um usuário através do identificador.")
	public ResponseEntity<UsuarioResponseDTO> inativarUsuario(
			@ApiParam(value = "ID do usuário", example = "01") Long id) throws Exception;
	
	@ApiOperation("Habilitar um usuário através do identificador.")
	public ResponseEntity<UsuarioResponseDTO> ativarUsuario(
			@ApiParam(value = "ID do usuário", example = "01") Long id) throws Exception;
}
