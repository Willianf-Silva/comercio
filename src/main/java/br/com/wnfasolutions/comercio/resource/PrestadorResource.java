package br.com.wnfasolutions.comercio.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wnfasolutions.comercio.dto.request.PrestadorRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.PrestadorResponseDTO;
import br.com.wnfasolutions.comercio.event.ResourceCreatedEvent;
import br.com.wnfasolutions.comercio.resource.swagger.PrestadorResourceSwagger;
import br.com.wnfasolutions.comercio.service.PrestadorService;

@RestController
@RequestMapping("/api/v1/prestadores")
public class PrestadorResource extends ResourceBase<PrestadorResponseDTO> implements PrestadorResourceSwagger {

	@Autowired
	PrestadorService prestadorService;

	@Autowired
	private ApplicationEventPublisher publicarEvento;

	@PostMapping
	public ResponseEntity<PrestadorResponseDTO> cadastrarPrestador(@RequestBody @Valid PrestadorRequestDTO prestadorRequestDTO,
			HttpServletResponse resp) throws Exception {
		PrestadorResponseDTO response = prestadorService.cadastrarPrestador(prestadorRequestDTO);
		publicarEvento.publishEvent(new ResourceCreatedEvent(this, resp, response.getId()));
		return responderItemCriado(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PrestadorResponseDTO> atualizarPrestador(@PathVariable Long id,
			@RequestBody @Valid PrestadorRequestDTO prestadorRequestDTO) throws Exception {

		PrestadorResponseDTO response = prestadorService.atualizarPrestador(id, prestadorRequestDTO);
		return responderSucessoComItem(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PrestadorResponseDTO> buscarPorId(@PathVariable Long id) throws Exception {
		PrestadorResponseDTO response = prestadorService.buscarPorId(id);
		return responderSucessoComItem(response);
	}
	
	@PatchMapping("/inativar/{id}")
	public ResponseEntity<PrestadorResponseDTO> inativarPrestador(@PathVariable Long id) throws Exception {
		prestadorService.inativarPrestador(id);
		return responderSucesso();
	}
	
	@PatchMapping("/ativar/{id}")
	public ResponseEntity<PrestadorResponseDTO> ativarPrestador(@PathVariable Long id) throws Exception {
		prestadorService.ativarPrestador(id);
		return responderSucesso();
	}
}
