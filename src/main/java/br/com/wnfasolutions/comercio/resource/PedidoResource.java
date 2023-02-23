package br.com.wnfasolutions.comercio.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wnfasolutions.comercio.dto.request.PedidoRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.PedidoResponseDTO;
import br.com.wnfasolutions.comercio.event.ResourceCreatedEvent;
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
}
