package br.com.wnfasolutions.comercio.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wnfasolutions.comercio.dto.request.OrcamentoRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.OrcamentoResponseDTO;
import br.com.wnfasolutions.comercio.event.ResourceCreatedEvent;
import br.com.wnfasolutions.comercio.repository.filtro.OrcamentoFiltro;
import br.com.wnfasolutions.comercio.resource.swagger.OrcamentoResourceSwagger;
import br.com.wnfasolutions.comercio.service.OrcamentoService;

@RestController
@RequestMapping("/api/v1/orcamentos")
public class OrcamentoResource extends ResourceBase<OrcamentoResponseDTO> implements OrcamentoResourceSwagger {

	@Autowired
	private OrcamentoService orcamentoService;

	@Autowired
	private ApplicationEventPublisher publicarEvento;

	@PostMapping
	public ResponseEntity<OrcamentoResponseDTO> cadastrarOrcamento(
			@RequestBody @Valid OrcamentoRequestDTO orcamentoRequestDTO, HttpServletResponse resp) throws Exception {
		OrcamentoResponseDTO response = orcamentoService.cadastrarOrcamento(orcamentoRequestDTO);
		publicarEvento.publishEvent(new ResourceCreatedEvent(this, resp, response.getId()));
		return responderItemCriado(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<OrcamentoResponseDTO> atualizarOrcamento(@PathVariable Long id,
			@RequestBody @Valid OrcamentoRequestDTO orcamentoRequestDTO) throws Exception {
		OrcamentoResponseDTO response = orcamentoService.atualizarOrcamento(id, orcamentoRequestDTO);
		return responderSucessoComItem(response);
	}
	
	@GetMapping
	public ResponseEntity<Page<OrcamentoResponseDTO>> buscarOrcamentos(OrcamentoFiltro orcamentoFiltro, Pageable pageable){
		Page<OrcamentoResponseDTO> response = orcamentoService.buscarOrcamentos(orcamentoFiltro, pageable);
		return responderListaDeItensPaginada(response);
	}
	
	@PatchMapping("/reprovar/{id}")
	public ResponseEntity<?> reprovarOrcamento(@PathVariable Long id) throws Exception {
		orcamentoService.reprovarOrcamento(id);
		return responderSucessoSemItem();
	}
}
