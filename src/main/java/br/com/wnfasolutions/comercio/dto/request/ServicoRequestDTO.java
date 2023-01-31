package br.com.wnfasolutions.comercio.dto.request;

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
public class ServicoRequestDTO{

	@ApiModelProperty(notes = "Nome do serviço", required = true, example = "Barra de calça jeans")
	@NotBlank
    private String nome;

	@ApiModelProperty(notes = "Valor do serviço", required = true, example = "20.00")
    @DecimalMin("0.0")
    private double valor;

}