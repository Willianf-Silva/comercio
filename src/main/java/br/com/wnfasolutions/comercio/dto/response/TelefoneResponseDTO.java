package br.com.wnfasolutions.comercio.dto.response;

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
public class TelefoneResponseDTO {
	@ApiModelProperty(notes = "Identificador único para o telefone de contato", required = true, example = "01")
	private Long id;

	@ApiModelProperty(notes = "Código DDI País do telefone de contato", required = true, example = "51")
	private String ddi;

	@ApiModelProperty(notes = "Código DDD do telefone de contato", required = true, example = "19")
	private String ddd;

	@ApiModelProperty(notes = "Número do celular do telefone de contato", required = false, example = "988772233")
	private String celular;

	@ApiModelProperty(notes = "Número do telefone de contato", required = false, example = "38347793")
	private String telefone;
}
