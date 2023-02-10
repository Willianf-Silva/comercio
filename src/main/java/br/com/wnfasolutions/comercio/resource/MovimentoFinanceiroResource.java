package br.com.wnfasolutions.comercio.resource;

import java.util.List;

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

import br.com.wnfasolutions.comercio.dto.request.MovimentoFinanceiroRequestDTO;
import br.com.wnfasolutions.comercio.dto.request.MovimentosFinanceiroPagamentoDTO;
import br.com.wnfasolutions.comercio.dto.response.MovimentoFinanceiroResponseDTO;
import br.com.wnfasolutions.comercio.event.ResourceCreatedEvent;
import br.com.wnfasolutions.comercio.repository.filtro.MovimentoFinanceiroFiltro;
import br.com.wnfasolutions.comercio.resource.swagger.MovimentoFinanceiroResourceSwagger;
import br.com.wnfasolutions.comercio.service.MovimentoFinanceiroService;

@RestController
@RequestMapping("/api/v1/financeiros")
public class MovimentoFinanceiroResource extends ResourceBase<MovimentoFinanceiroResponseDTO> implements MovimentoFinanceiroResourceSwagger {

	@Autowired
	MovimentoFinanceiroService movimentoFinanceiroService;

	@Autowired
	private ApplicationEventPublisher publicarEvento;

	@PostMapping
	public ResponseEntity<MovimentoFinanceiroResponseDTO> cadastrarMovimentoFinanceiro(@RequestBody @Valid MovimentoFinanceiroRequestDTO movimentoFinanceiroRequestDTO,
			HttpServletResponse resp) throws Exception {
		MovimentoFinanceiroResponseDTO response = movimentoFinanceiroService.cadastrarMovimentoFinanceiro(movimentoFinanceiroRequestDTO);
		publicarEvento.publishEvent(new ResourceCreatedEvent(this, resp, response.getId()));
		return responderItemCriado(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MovimentoFinanceiroResponseDTO> atualizarMovimentoFinanceiro(@PathVariable Long id,
			@RequestBody @Valid MovimentoFinanceiroRequestDTO movimentoFinanceiroRequestDTO) throws Exception {

		MovimentoFinanceiroResponseDTO response = movimentoFinanceiroService.atualizarMovimentoFinanceiro(id, movimentoFinanceiroRequestDTO);
		return responderSucessoComItem(response);
	}
	
	@PutMapping("/pagamentos")
	public ResponseEntity<?> pagarMovimentos(@RequestBody List<MovimentosFinanceiroPagamentoDTO> movimentosFinanceiroPagamentoDTO) throws Exception{
		movimentoFinanceiroService.pagarMovimentos(movimentosFinanceiroPagamentoDTO);
		return responderSucessoSemItem();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MovimentoFinanceiroResponseDTO> buscarPorId(@PathVariable Long id) throws Exception {
		MovimentoFinanceiroResponseDTO response = movimentoFinanceiroService.buscarPorId(id);
		return responderSucessoComItem(response);
	}
	
	@GetMapping
	public ResponseEntity<Page<MovimentoFinanceiroResponseDTO>> buscarMovimentosFinanceiro(MovimentoFinanceiroFiltro movimentoFinanceiroFiltro, Pageable pageable){
		Page<MovimentoFinanceiroResponseDTO> response = movimentoFinanceiroService.buscarMovimentosFinanceiro(movimentoFinanceiroFiltro, pageable);
		return responderListaDeItensPaginada(response );
	}
	
	@PatchMapping("/inativar/{id}")
	public ResponseEntity<MovimentoFinanceiroResponseDTO> inativarMovimentoFinanceiro(@PathVariable Long id) throws Exception {
		movimentoFinanceiroService.inativarMovimentoFinanceiro(id);
		return responderSucesso();
	}
	
	@PatchMapping("/ativar/{id}")
	public ResponseEntity<MovimentoFinanceiroResponseDTO> ativarMovimentoFinanceiro(@PathVariable Long id) throws Exception {
		movimentoFinanceiroService.ativarMovimentoFinanceiro(id);
		return responderSucesso();
	}
}
