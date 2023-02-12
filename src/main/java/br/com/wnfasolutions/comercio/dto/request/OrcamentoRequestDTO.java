package br.com.wnfasolutions.comercio.dto.request;

import java.util.List;

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
public class OrcamentoRequestDTO {

	@ApiModelProperty(notes = "Item contendo a quantidade e o serviço vendido.", required = true)
	@NotNull
	private List<ItemServicoRequestDTO> itensServico;

	@ApiModelProperty(notes = "Identificador único do cliente.", required = true, example = "01")
	@NotNull
	private Long idCliente;

	@ApiModelProperty(notes = "Identificador único do usuario.", required = true, example = "01")
	@NotNull
	private Long idUsuario;

}