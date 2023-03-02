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

import br.com.wnfasolutions.comercio.dto.request.ClienteRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.ClienteResponseDTO;
import br.com.wnfasolutions.comercio.event.ResourceCreatedEvent;
import br.com.wnfasolutions.comercio.repository.filtro.ClienteFiltro;
import br.com.wnfasolutions.comercio.resource.swagger.ClienteResourceSwagger;
import br.com.wnfasolutions.comercio.service.ClienteService;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteResource extends ResourceBase<ClienteResponseDTO> implements ClienteResourceSwagger {

	@Autowired
	ClienteService clienteService;

	@Autowired
	private ApplicationEventPublisher publicarEvento;

	@PostMapping
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<ClienteResponseDTO> cadastrarCliente(@RequestBody @Valid ClienteRequestDTO clienteRequestDTO,
			HttpServletResponse resp) throws Exception {
		ClienteResponseDTO response = clienteService.cadastrarCliente(clienteRequestDTO);
		publicarEvento.publishEvent(new ResourceCreatedEvent(this, resp, response.getId()));
		return responderItemCriado(response);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<ClienteResponseDTO> atualizarCliente(@PathVariable Long id,
			@RequestBody @Valid ClienteRequestDTO clienteRequestDTO) throws Exception {

		ClienteResponseDTO response = clienteService.atualizarCliente(id, clienteRequestDTO);
		return responderSucessoComItem(response);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_READING') and #oauth2.hasScope('read')")
	public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) throws Exception {
		ClienteResponseDTO response = clienteService.buscarPorId(id);
		return responderSucessoComItem(response);
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_READING') and #oauth2.hasScope('read')")
	public ResponseEntity<Page<ClienteResponseDTO>> buscarClientes(ClienteFiltro clienteFiltro, Pageable pageable){
		Page<ClienteResponseDTO> response = clienteService.buscarClientes(clienteFiltro, pageable);
		return responderListaDeItensPaginada(response);
	}
	
	@PatchMapping("/inativar/{id}")
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<ClienteResponseDTO> inativarCliente(@PathVariable Long id) throws Exception {
		clienteService.inativarCliente(id);
		return responderSucesso();
	}
	
	@PatchMapping("/ativar/{id}")
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<ClienteResponseDTO> ativarCliente(@PathVariable Long id) throws Exception {
		clienteService.ativarCliente(id);
		return responderSucesso();
	}
}
