package br.com.wnfasolutions.comercio.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServicoRequestDTO {

	@ApiModelProperty(notes = "Nome do serviço", required = true, example = "Barra de calça jeans")
	@NotBlank
	private String nome;

	@ApiModelProperty(notes = "Descrição do serviço", required = false, example = "Serviço para realizar barra simples em calça jeans")
	private String descricao;

	@ApiModelProperty(notes = "Valor do custo para a realização do serviço", required = true, example = "10.00")
	@DecimalMin("0.0")
	private BigDecimal valorCusto;

	@ApiModelProperty(notes = "Valor de venda determinado para o serviço", required = true, example = "20.00")
	@DecimalMin("0.0")
	private BigDecimal valorVenda;

}