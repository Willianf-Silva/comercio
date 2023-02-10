package br.com.wnfasolutions.comercio.resource.swagger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.com.wnfasolutions.comercio.dto.request.ServicoRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.ServicoResponseDTO;
import br.com.wnfasolutions.comercio.repository.filtro.ServicoFiltro;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Servicos")
public interface ServicoResourceSwagger {

	@ApiOperation("Incluir um novo servico.")
	public ResponseEntity<ServicoResponseDTO> cadastrarServico(
			@ApiParam("Informações do servicos que será incluído.") ServicoRequestDTO servicoRequestDTO,
			HttpServletResponse resp) throws Exception;
	
	@ApiOperation("Atualizar um servico existente.")
	public ResponseEntity<ServicoResponseDTO> atualizarServico(
			@ApiParam(value = "ID do servico", example = "01") Long id,
			@ApiParam("Informações do servicos que será incluído.") ServicoRequestDTO servicoRequestDTO) throws Exception;
	
	@ApiOperation("Buscar um servico através do identificador.")
	public ResponseEntity<ServicoResponseDTO> buscarPorId(
			@ApiParam(value = "ID do servico", example = "01") Long id) throws Exception;
	
	@ApiImplicitParams({
        @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                value = "Quantidade de registros", defaultValue = "1"),
        @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                value = "Pagina a ser carregada", defaultValue = "0"),
        @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                value = "Ordenação dos registros")
	})
	@ApiOperation("Buscar servicos com filtro")
	public ResponseEntity<Page<ServicoResponseDTO>> buscarServicos(
			@ApiParam("Opções de filtro para consulta") ServicoFiltro servicoFiltro, 
			@ApiIgnore Pageable pageable);
	
	@ApiOperation("Inativar um servico através do identificador.")
	public ResponseEntity<ServicoResponseDTO> inativarServico(
			@ApiParam(value = "ID do servico", example = "01") Long id) throws Exception;
	
	@ApiOperation("Ativar um servico através do identificador.")
	public ResponseEntity<ServicoResponseDTO> ativarServico(
			@ApiParam(value = "ID do servico", example = "01") Long id) throws Exception;
}
