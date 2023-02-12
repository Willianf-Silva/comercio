package br.com.wnfasolutions.comercio.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.wnfasolutions.comercio.dto.request.UsuarioRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.UsuarioResponseDTO;
import br.com.wnfasolutions.comercio.entity.UsuarioDO;
import br.com.wnfasolutions.comercio.repository.filtro.UsuarioFiltro;

public interface UsuarioService {

	UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO usuarioRequestDTO) throws Exception;

	UsuarioResponseDTO atualizarUsuario(Long id, UsuarioRequestDTO usuarioRequestDTO) throws Exception;

	UsuarioResponseDTO buscarPorId(Long id) throws Exception;

	void inativarUsuario(Long id) throws Exception;

	void ativarUsuario(Long id) throws Exception;

	Page<UsuarioResponseDTO> buscarUsuarios(UsuarioFiltro usuarioFiltro, Pageable pageable);
	
	UsuarioDO buscarUsuarioAtivoPorId(Long id) throws Exception;

}
