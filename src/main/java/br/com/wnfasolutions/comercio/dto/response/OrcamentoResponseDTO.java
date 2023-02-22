package br.com.wnfasolutions.comercio.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.DecimalMin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrcamentoResponseDTO {

	@ApiModelProperty(notes = "Identificador único do orçamento.", required = true, example = "01")
	private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@ApiModelProperty(notes = "Data de realização do orçamento.", required = true, example = "21/07/2050")
	private LocalDateTime dataInclusao;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@ApiModelProperty(notes = "Data da última alterção do orçamento.", required = false, example = "21/07/2050")
	private LocalDateTime dataAlteracao;

	@ApiModelProperty(notes = "Valor do orçamento.", required = true, example = "100.00")
	@DecimalMin("0.0")
	private BigDecimal valor;
	
	@ApiModelProperty(notes = "Situação atual do orçamento.", required = true)
	private String situacaoOrcamento;

	@ApiModelProperty(notes = "Dados do cliente.", required = true)
	private ClienteResponseDTO cliente;

	@ApiModelProperty(notes = "Dados do usuário.", required = true)
	private UsuarioResponseDTO usuario;
	
	@ApiModelProperty(notes = "Item contendo a quantidade e o serviço vendido.", required = true)
	private List<ItemServicoResponseDTO> itensServico;
}