package br.com.wnfasolutions.comercio.resource.swagger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.com.wnfasolutions.comercio.dto.request.OrcamentoRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.OrcamentoResponseDTO;
import br.com.wnfasolutions.comercio.repository.filtro.OrcamentoFiltro;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Pedidos")
public interface OrcamentoResourceSwagger {

	@ApiOperation("Incluir um novo orçamento.")
	public ResponseEntity<OrcamentoResponseDTO> cadastrarOrcamento(
			@ApiParam("Informações do orçamento que será incluído.") OrcamentoRequestDTO orcamentoRequestDTO,
			HttpServletResponse resp) throws Exception;

	@ApiOperation("Altera o status do orçamento existente e inclui um novo orçamento.")
	public ResponseEntity<OrcamentoResponseDTO> atualizarOrcamento(
			@ApiParam(value = "ID do orçamento", example = "01") Long id,
			@ApiParam("Dados do orçamento que será alterado.") OrcamentoRequestDTO orcamentoRequestDTO)
			throws Exception;
	
	@ApiImplicitParams({
        @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                value = "Quantidade de registros", defaultValue = "1"),
        @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                value = "Pagina a ser carregada", defaultValue = "0"),
        @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                value = "Ordenação dos registros")
	})
	@ApiOperation("Buscar orcamentos com filtro")
	public ResponseEntity<Page<OrcamentoResponseDTO>> buscarOrcamentos(
			@ApiParam("Opções de filtro para consulta") OrcamentoFiltro orcamentoFiltro, 
			@ApiIgnore Pageable pageable);
	
	@ApiOperation("Reprova um orçamento através do identificador.")
	public ResponseEntity<?> reprovarOrcamento(
			@ApiParam(value = "ID do orçamento", example = "01") Long id) throws Exception;
}
