package br.com.wnfasolutions.comercio.resource.swagger;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import br.com.wnfasolutions.comercio.dto.request.MovimentoFinanceiroRequestDTO;
import br.com.wnfasolutions.comercio.dto.request.MovimentosFinanceiroPagamentoDTO;
import br.com.wnfasolutions.comercio.dto.response.MovimentoFinanceiroResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Financeiro")
public interface MovimentoFinanceiroResourceSwagger {

	@ApiOperation("Incluir um novo movimento financeiro.")
	public ResponseEntity<MovimentoFinanceiroResponseDTO> cadastrarMovimentoFinanceiro(
			@ApiParam("Informações do movimentos financeiro que será incluído.") MovimentoFinanceiroRequestDTO movimentoFinanceiroRequestDTO,
			HttpServletResponse resp) throws Exception;
	
	@ApiOperation("Atualizar um movimento financeiro existente.")
	public ResponseEntity<MovimentoFinanceiroResponseDTO> atualizarMovimentoFinanceiro(
			@ApiParam(value = "ID do movimento financeiro", example = "01") Long id,
			@ApiParam("Informações do movimento financeiro que será incluído.") MovimentoFinanceiroRequestDTO movimentoFinanceiroRequestDTO) throws Exception;
	
	@ApiOperation("Atualiza as informações de pagamento para uma lista de movimentos financeiro existente.")
	public ResponseEntity<?> pagarMovimentos(
			@ApiParam(value = "Lista de movimentos financeiro", example = "01") List<MovimentosFinanceiroPagamentoDTO> movimentosFinanceiroPagamentoDTO) throws Exception;
	
	@ApiOperation("Buscar um movimento financeiro através do identificador.")
	public ResponseEntity<MovimentoFinanceiroResponseDTO> buscarPorId(
			@ApiParam(value = "ID do movimento financeiro", example = "01") Long id) throws Exception;
	
	@ApiOperation("Inativar um movimento financeiro através do identificador.")
	public ResponseEntity<MovimentoFinanceiroResponseDTO> inativarMovimentoFinanceiro(
			@ApiParam(value = "ID do movimento financeiro", example = "01") Long id) throws Exception;
	
	@ApiOperation("Ativar um movimento financeiro através do identificador.")
	public ResponseEntity<MovimentoFinanceiroResponseDTO> ativarMovimentoFinanceiro(
			@ApiParam(value = "ID do movimento financeiro", example = "01") Long id) throws Exception;
}
