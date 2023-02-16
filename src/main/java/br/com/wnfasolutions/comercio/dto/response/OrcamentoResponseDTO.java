package br.com.wnfasolutions.comercio.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.wnfasolutions.comercio.enuns.StatusOrcamento;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrcamentoResponseDTO {

	@ApiModelProperty(notes = "Identificador único do orçamento.", required = true, example = "01")
	private Long id;

	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(notes = "Data de realização do orçamento.", required = true, example = "21/07/2050")
	private LocalDate dataInclusao;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(notes = "Data da última alterção do orçamento.", required = false, example = "21/07/2050")
	private LocalDate dataAlteracao;

	@ApiModelProperty(notes = "Valor do orçamento.", required = true, example = "100.00")
	@DecimalMin("0.0")
	private BigDecimal valor;

	@ApiModelProperty(notes = "Item contendo a quantidade e o serviço vendido.", required = true)
	@NotNull
	private List<ItemServicoResponseDTO> itensServico;

	@ApiModelProperty(notes = "Dados do cliente.", required = true)
	@NotNull
	private ClienteResponseDTO cliente;

	@ApiModelProperty(notes = "Dados do usuário.", required = true)
	@NotNull
	private UsuarioResponseDTO usuario;

	@ApiModelProperty(notes = "Situação atual do orçamento.", required = true)
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusOrcamento statusOrcamento;
}