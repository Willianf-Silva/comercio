package br.com.wnfasolutions.comercio.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wnfasolutions.comercio.dto.request.UsuarioRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.UsuarioResponseDTO;
import br.com.wnfasolutions.comercio.event.ResourceCreatedEvent;
import br.com.wnfasolutions.comercio.repository.filtro.UsuarioFiltro;
import br.com.wnfasolutions.comercio.resource.swagger.UsuarioResourceSwagger;
import br.com.wnfasolutions.comercio.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioResource extends ResourceBase<UsuarioResponseDTO> implements UsuarioResourceSwagger {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	private ApplicationEventPublisher publicarEvento;

	@PostMapping
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO,
			HttpServletResponse resp) throws Exception {
		UsuarioResponseDTO response = usuarioService.cadastrarUsuario(usuarioRequestDTO);
		publicarEvento.publishEvent(new ResourceCreatedEvent(this, resp, response.getId()));
		return responderItemCriado(response);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable Long id,
			@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) throws Exception {

		UsuarioResponseDTO response = usuarioService.atualizarUsuario(id, usuarioRequestDTO);
		return responderSucessoComItem(response);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_READING') and #oauth2.hasScope('read')")
	public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) throws Exception {
		UsuarioResponseDTO response = usuarioService.buscarPorId(id);
		return responderSucessoComItem(response);
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_READING') and #oauth2.hasScope('read')")
	public ResponseEntity<Page<UsuarioResponseDTO>> buscarUsuarios(UsuarioFiltro usuarioFiltro, Pageable pageable){
		Page<UsuarioResponseDTO> response = usuarioService.buscarUsuarios(usuarioFiltro, pageable);
		return responderListaDeItensPaginada(response);
	}
	
	@PatchMapping("/inativar/{id}")
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<UsuarioResponseDTO> inativarUsuario(@PathVariable Long id) throws Exception {
		usuarioService.inativarUsuario(id);
		return responderSucesso();
	}
	
	@PatchMapping("/ativar/{id}")
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<UsuarioResponseDTO> ativarUsuario(@PathVariable Long id) throws Exception {
		usuarioService.ativarUsuario(id);
		return responderSucesso();
	}
}
