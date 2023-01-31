package br.com.wnfasolutions.comercio.resource.swagger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import br.com.wnfasolutions.comercio.dto.request.ServicoRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.ServicoResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
	
	@ApiOperation("Inativar um servico através do identificador.")
	public ResponseEntity<ServicoResponseDTO> inativarServico(
			@ApiParam(value = "ID do servico", example = "01") Long id) throws Exception;
	
	@ApiOperation("Ativar um servico através do identificador.")
	public ResponseEntity<ServicoResponseDTO> ativarServico(
			@ApiParam(value = "ID do servico", example = "01") Long id) throws Exception;
}
