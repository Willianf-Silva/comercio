package br.com.wnfasolutions.comercio.dto.response;

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
public class TelefoneResponseDTO {
	@NotNull
	@ApiModelProperty(notes = "Identificador único para o telefone de contato", required = true, example = "01")
	private Long id;

	@NotNull
	@ApiModelProperty(notes = "Código DDI País do telefone de contato", required = true, example = "51")
	private String ddi;

	@NotNull
	@ApiModelProperty(notes = "Código DDD do telefone de contato", required = true, example = "19")
	private String ddd;

	@ApiModelProperty(notes = "Número do celular do telefone de contato", required = false, example = "988772233")
	private String celular;

	@ApiModelProperty(notes = "Número do telefone de contato", required = false, example = "38347793")
	private String telefone;
}
