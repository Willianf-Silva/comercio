package br.com.wnfasolutions.comercio.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wnfasolutions.comercio.dto.request.OrcamentoRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.OrcamentoResponseDTO;
import br.com.wnfasolutions.comercio.event.ResourceCreatedEvent;
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
}
