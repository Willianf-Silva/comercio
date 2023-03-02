package br.com.wnfasolutions.comercio.resource.swagger;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.com.wnfasolutions.comercio.dto.request.MovimentoFinanceiroRequestDTO;
import br.com.wnfasolutions.comercio.dto.request.MovimentosFinanceiroPagamentoDTO;
import br.com.wnfasolutions.comercio.dto.response.MovimentoFinanceiroResponseDTO;
import br.com.wnfasolutions.comercio.repository.filtro.MovimentoFinanceiroFiltro;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Financeiro")
public interface MovimentoFinanceiroResourceSwagger {

	@ApiOperation("Incluir um novo movimento financeiro.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<MovimentoFinanceiroResponseDTO> cadastrarMovimentoFinanceiro(
			@ApiParam("Informações do movimentos financeiro que será incluído.") MovimentoFinanceiroRequestDTO movimentoFinanceiroRequestDTO,
			HttpServletResponse resp) throws Exception;
	
	@ApiOperation("Atualizar um movimento financeiro existente.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<MovimentoFinanceiroResponseDTO> atualizarMovimentoFinanceiro(
			@ApiParam(value = "ID do movimento financeiro", example = "01") Long id,
			@ApiParam("Informações do movimento financeiro que será incluído.") MovimentoFinanceiroRequestDTO movimentoFinanceiroRequestDTO) throws Exception;
	
	@ApiOperation("Atualiza as informações de pagamento para uma lista de movimentos financeiro existente.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<?> pagarMovimentos(
			@ApiParam(value = "Lista de movimentos financeiro", example = "01") List<MovimentosFinanceiroPagamentoDTO> movimentosFinanceiroPagamentoDTO) throws Exception;
	
	@ApiOperation("Buscar um movimento financeiro através do identificador.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<MovimentoFinanceiroResponseDTO> buscarPorId(
			@ApiParam(value = "ID do movimento financeiro", example = "01") Long id) throws Exception;
	
	@ApiImplicitParams({
        @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                value = "Quantidade de registros", defaultValue = "1"),
        @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                value = "Pagina a ser carregada", defaultValue = "0"),
        @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                value = "Ordenação dos registros")
	})
	@ApiOperation("Buscar movimentos financeiro com filtro")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<Page<MovimentoFinanceiroResponseDTO>> buscarMovimentosFinanceiro(
			@ApiParam("Opções de filtro para consulta") MovimentoFinanceiroFiltro movimentoFinanceiroFiltro, 
			@ApiIgnore Pageable pageable);
	
	@ApiOperation("Inativar um movimento financeiro através do identificador.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<MovimentoFinanceiroResponseDTO> inativarMovimentoFinanceiro(
			@ApiParam(value = "ID do movimento financeiro", example = "01") Long id) throws Exception;
	
	@ApiOperation("Ativar um movimento financeiro através do identificador.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<MovimentoFinanceiroResponseDTO> ativarMovimentoFinanceiro(
			@ApiParam(value = "ID do movimento financeiro", example = "01") Long id) throws Exception;
}
