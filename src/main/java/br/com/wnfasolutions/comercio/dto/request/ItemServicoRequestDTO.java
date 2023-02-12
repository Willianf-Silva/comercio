package br.com.wnfasolutions.comercio.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemServicoRequestDTO {

	@ApiModelProperty(notes = "Identificador do serviço vendido.", required = true, example = "01")
	@NotNull
	private Long servicoId;

	@ApiModelProperty(notes = "Quantidade do serviço vendido.", required = true, example = "02")
	@Min(value = 1)
	private Integer quantidade;

	@ApiModelProperty(notes = "Desconto percentual que deve ser aplicado para esse item.", required = false, example = "20")
	@Min(value = 0)
	private Integer desconto;

	@ApiModelProperty(notes = "Valor unitário do serviço que foi vendido.", required = true, example = "02")
	@DecimalMin("0.0")
	private BigDecimal valorVendido;
}