package br.com.wnfasolutions.comercio.repository.filtro;

import br.com.wnfasolutions.comercio.enuns.Situacao;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServicoFiltro {

	@ApiModelProperty(notes = "Nome do serviço", required = false, example = "Barra de calça jeans")
	private String nome;

	@ApiModelProperty(notes = "Descrição do serviço", required = false, example = "Serviço para realizar barra simples em calça jeans")
	private String descricao;
	
	@ApiModelProperty(notes = "Situação atual do movimento financeiro", required = false, example = "ATIVO")
	private Situacao situacao;
}
