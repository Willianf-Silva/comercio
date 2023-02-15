package br.com.wnfasolutions.comercio.dto.response;

import java.math.BigDecimal;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.wnfasolutions.comercio.enuns.Situacao;
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
public class ServicoResponseDTO {

	@ApiModelProperty(notes = "Identificador único para o serviço", required = true, example = "01")
	private Long id;

	@ApiModelProperty(notes = "Nome do serviço", required = true, example = "Barra de calça jeans")
	private String nome;

	@ApiModelProperty(notes = "Descrição do serviço", required = false, example = "Serviço para realizar barra simples em calça jeans")
	private String descricao;

	@ApiModelProperty(notes = "Valor do custo para a realização do serviço", required = true, example = "3.00")
	@DecimalMin("0.0")
	private BigDecimal valorCusto;

	@ApiModelProperty(notes = "Valor de venda determinado para o serviço", required = true, example = "20.00")
	@DecimalMin("0.0")
	private BigDecimal valorVenda;

	@ApiModelProperty(notes = "Lucro em Reais", required = true, example = "17.00")
	@DecimalMin("0.0")
	private BigDecimal lucroMonetario;

	@ApiModelProperty(notes = "Lucro em Percentual", required = true, example = "566,67")
	@DecimalMin("0.0")
	private BigDecimal lucroPercentual;

	@ApiModelProperty(notes = "Situação atual do serviço", required = true, example = "ATIVO")
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
}
