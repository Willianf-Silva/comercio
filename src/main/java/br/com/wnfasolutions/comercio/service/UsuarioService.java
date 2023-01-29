package br.com.wnfasolutions.comercio.service;

import br.com.wnfasolutions.comercio.dto.request.UsuarioRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.UsuarioResponseDTO;

public interface UsuarioService {

	UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO usuarioRequestDTO) throws Exception;

	UsuarioResponseDTO atualizarUsuario(Long id, UsuarioRequestDTO usuarioRequestDTO) throws Exception;

}
