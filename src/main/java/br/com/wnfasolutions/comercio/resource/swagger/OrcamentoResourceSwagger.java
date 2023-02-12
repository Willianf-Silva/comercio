package br.com.wnfasolutions.comercio.resource.swagger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import br.com.wnfasolutions.comercio.dto.request.OrcamentoRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.OrcamentoResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
}
