package br.com.wnfasolutions.comercio.resource.swagger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import br.com.wnfasolutions.comercio.dto.request.UsuarioRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.UsuarioResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Usuario")
public interface UsuarioResourceSwagger {

	@ApiOperation("Incluir um novo usuário.")
	public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(
			@ApiParam("Informações do usuários que será incluído.") UsuarioRequestDTO usuarioRequestDTO,
			HttpServletResponse resp) throws Exception;
	
	@ApiOperation("Atualizar um usuário existente.")
	public ResponseEntity<UsuarioResponseDTO> updateVeterinarian(
			@ApiParam(value = "ID do usuário", example = "01") Long id,
			@ApiParam("Informações do usuários que será incluído.") UsuarioRequestDTO usuarioRequestDTO) throws Exception;
}
