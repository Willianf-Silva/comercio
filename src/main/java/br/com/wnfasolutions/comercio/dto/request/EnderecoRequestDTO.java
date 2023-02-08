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
public class EnderecoRequestDTO {

	@NotNull
	@ApiModelProperty(notes = "Nome da rua e número da casa do endereço de contato", required = true, example = "Rua Dirley Silva, 234")
	private String endereco;

	@NotNull
	@ApiModelProperty(notes = "Nome do bairro do endereço de contato", required = true, example = "Morada do Sol")
	private String bairro;

	@NotNull
	@ApiModelProperty(notes = "CEP do endereço de contato", required = true, example = "13348772")
	private String cep;

	@NotNull
	@ApiModelProperty(notes = "Complemento do endereço de contato", required = false, example = "Apartamento")
	private String complemento;

	@NotNull
	@ApiModelProperty(notes = "Cidade do endereço de contato", required = true, example = "Sorocaba")
	private String cidade;

	@NotNull
	@ApiModelProperty(notes = "UF do endereço de contato", required = true, example = "São Paulo")
	private String estado;
}
