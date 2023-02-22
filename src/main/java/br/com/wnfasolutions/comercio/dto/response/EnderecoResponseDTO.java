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
public class EnderecoResponseDTO {

	@ApiModelProperty(notes = "Identificador único para o endereço de contato", required = true, example = "01")
	private Long id;

	@ApiModelProperty(notes = "Nome da rua e número da casa do endereço de contato", required = true, example = "Rua Dirley Silva, 234")
	private String endereco;

	@ApiModelProperty(notes = "Nome do bairro do endereço de contato", required = true, example = "Morada do Sol")
	private String bairro;

	@ApiModelProperty(notes = "CEP do endereço de contato", required = true, example = "13348772")
	private String cep;

	@ApiModelProperty(notes = "Complemento do endereço de contato", required = false, example = "Apartamento")
	private String complemento;

	@ApiModelProperty(notes = "Cidade do endereço de contato", required = true, example = "Sorocaba")
	private String cidade;

	@ApiModelProperty(notes = "UF do endereço de contato", required = true, example = "São Paulo")
	private String estado;
}
