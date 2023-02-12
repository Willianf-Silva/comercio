package br.com.wnfasolutions.comercio.dto.response;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.NumberFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemServicoResponseDTO {

	@ApiModelProperty(notes = "Identificador único para cada item.", required = true, example = "01")
	private Long id;

	@ApiModelProperty(notes = "Dados do serviço vendido.", required = true)
	private ServicoResponseDTO servico;

	@ApiModelProperty(notes = "Quantidade do serviço vendido.", required = true, example = "02")
	@Min(value = 1)
	private Integer quantidade;

	@ApiModelProperty(notes = "Desconto percentual que deve ser aplicado para esse item.", required = false, example = "20")
	@Min(value = 0)
	private Integer desconto;

	@ApiModelProperty(notes = "Valor unitário que foi vendido.", required = true, example = "02")
	@DecimalMin("0.0")
	private BigDecimal valorVendido;

	@ApiModelProperty(notes = "Valor total para o serviço vendido.", required = true)
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal soma;
}
