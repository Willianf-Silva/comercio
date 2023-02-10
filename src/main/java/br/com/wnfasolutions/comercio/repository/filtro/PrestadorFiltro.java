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
public class PrestadorFiltro {
	
	@ApiModelProperty(notes = "Nome do cliente", required = false, example = "Willian")
	private String nome;
	
	@ApiModelProperty(notes = "Documento CPF ou CNPJ do prestador de serviço", required = false, example = "772.324.346-42")
	private String documento;
	
	@ApiModelProperty(notes = "E-mail de contato", required = false, example = "teste@test.com.br")
	private String email;
	
	@ApiModelProperty(notes = "Situação atual do movimento financeiro", required = false, example = "ATIVO")
	private Situacao situacao;
}
