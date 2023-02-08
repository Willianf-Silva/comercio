package br.com.wnfasolutions.comercio.dto.request;

import javax.validation.constraints.Email;
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
public class PrestadorRequestDTO{

	@NotNull
	@ApiModelProperty(notes = "Nome do prestador de serviço", required = true, example = "Willian")
	private String nome;
	
	@ApiModelProperty(notes = "Documento CPF ou CNPJ do prestador de serviço", required = true, example = "772.324.346-42")
    private String documento;
	
	@NotNull
	@Email
	@ApiModelProperty(notes = "E-mail do prestador de serviço", required = true, example = "teste@test.com.br")
	private String email;

	@NotNull
	@ApiModelProperty(notes = "Comissão do prestador de serviço", required = true, example = "25")
	private Integer comissao;
	
	@NotNull
	@ApiModelProperty(notes = "Telefone de contato", required = true)
	private TelefoneRequestDTO telefone;

	@NotNull
	@ApiModelProperty(notes = "Endereço de contato", required = true)
    private EnderecoRequestDTO endereco;
}