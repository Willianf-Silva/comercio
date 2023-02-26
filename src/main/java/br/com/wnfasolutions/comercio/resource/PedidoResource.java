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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wnfasolutions.comercio.dto.request.PedidoRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.PedidoResponseDTO;
import br.com.wnfasolutions.comercio.event.ResourceCreatedEvent;
import br.com.wnfasolutions.comercio.repository.filtro.PedidoFiltro;
import br.com.wnfasolutions.comercio.resource.swagger.PedidoResourceSwagger;
import br.com.wnfasolutions.comercio.service.PedidoService;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoResource extends ResourceBase<PedidoResponseDTO> implements PedidoResourceSwagger {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ApplicationEventPublisher publicarEvento;

	@PostMapping
	public ResponseEntity<PedidoResponseDTO> cadastrarOrcamento(
			@RequestBody @Valid PedidoRequestDTO pedidoRequestDTO, HttpServletResponse resp) throws Exception {
		PedidoResponseDTO response = pedidoService.cadastrarPedido(pedidoRequestDTO);
		publicarEvento.publishEvent(new ResourceCreatedEvent(this, resp, response.getId()));
		return responderItemCriado(response);
	}
	
	@GetMapping
	public ResponseEntity<Page<PedidoResponseDTO>> buscarPedidos(PedidoFiltro pedidoFiltro, Pageable pageable) throws Exception {
		Page<PedidoResponseDTO> response = pedidoService.buscarPedidos(pedidoFiltro, pageable);
		return responderListaDeItensPaginada(response);
	}
	
	@PatchMapping("/producao/{id}")
	public ResponseEntity<?> enviarPedidoProducao(@PathVariable Long id) throws Exception {
		pedidoService.enviarPedidoProducao(id);
		return responderSucessoSemItem();
	}
	
	@PatchMapping("/pronto/{id}")
	public ResponseEntity<?> pedidoAguardandoRetirada(@PathVariable Long id) throws Exception {
		pedidoService.pedidoAguardandoRetirada(id);
		return responderSucessoSemItem();
	}
	
	@PatchMapping("/entregue/{id}")
	public ResponseEntity<?> pedidoEntregue(@PathVariable Long id) throws Exception {
		pedidoService.pedidoEntregue(id);
		return responderSucessoSemItem();
	}
}
