package br.com.wnfasolutions.comercio.dto.request;

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
public class MovimentosFinanceiroPagamentoDTO {

	@NotNull
	@ApiModelProperty(notes = "ID do movimento financeiro que deve ser concluído.", required = true, example = "01")
	Long idMovimentoFinanceiro;
}
