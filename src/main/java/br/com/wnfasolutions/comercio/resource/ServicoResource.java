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

import br.com.wnfasolutions.comercio.dto.request.ServicoRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.ServicoResponseDTO;
import br.com.wnfasolutions.comercio.event.ResourceCreatedEvent;
import br.com.wnfasolutions.comercio.repository.filtro.ServicoFiltro;
import br.com.wnfasolutions.comercio.resource.swagger.ServicoResourceSwagger;
import br.com.wnfasolutions.comercio.service.ServicoService;

@RestController
@RequestMapping("/api/v1/servicos")
public class ServicoResource extends ResourceBase<ServicoResponseDTO> implements ServicoResourceSwagger {

	@Autowired
	ServicoService servicoService;

	@Autowired
	private ApplicationEventPublisher publicarEvento;

	@PostMapping
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<ServicoResponseDTO> cadastrarServico(@RequestBody @Valid ServicoRequestDTO servicoRequestDTO,
			HttpServletResponse resp) throws Exception {
		ServicoResponseDTO response = servicoService.cadastrarServico(servicoRequestDTO);
		publicarEvento.publishEvent(new ResourceCreatedEvent(this, resp, response.getId()));
		return responderItemCriado(response);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<ServicoResponseDTO> atualizarServico(@PathVariable Long id,
			@RequestBody @Valid ServicoRequestDTO servicoRequestDTO) throws Exception {

		ServicoResponseDTO response = servicoService.atualizarServico(id, servicoRequestDTO);
		return responderSucessoComItem(response);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_READING') and #oauth2.hasScope('read')")
	public ResponseEntity<ServicoResponseDTO> buscarPorId(@PathVariable Long id) throws Exception {
		ServicoResponseDTO response = servicoService.buscarPorId(id);
		return responderSucessoComItem(response);
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_READING') and #oauth2.hasScope('read')")
	public ResponseEntity<Page<ServicoResponseDTO>> buscarServicos(ServicoFiltro servicoFiltro, Pageable pageable){
		Page<ServicoResponseDTO> response = servicoService.buscarServicos(servicoFiltro, pageable);
		return responderListaDeItensPaginada(response);
	}
	
	@PatchMapping("/inativar/{id}")
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<ServicoResponseDTO> inativarServico(@PathVariable Long id) throws Exception {
		servicoService.inativarServico(id);
		return responderSucesso();
	}
	
	@PatchMapping("/ativar/{id}")
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<ServicoResponseDTO> ativarServico(@PathVariable Long id) throws Exception {
		servicoService.ativarServico(id);
		return responderSucesso();
	}
}
