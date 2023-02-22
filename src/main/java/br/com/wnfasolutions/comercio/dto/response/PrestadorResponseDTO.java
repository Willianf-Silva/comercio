package br.com.wnfasolutions.comercio.dto.response;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.wnfasolutions.comercio.enuns.Situacao;
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
public class PrestadorResponseDTO {

	@ApiModelProperty(notes = "Identificador único", required = true, example = "01")
	private Long id;

	@ApiModelProperty(notes = "Nome do cliente", required = true, example = "Willian")
	private String nome;

	@ApiModelProperty(notes = "Documento CPF ou CNPJ do prestador de serviço", required = true, example = "772.324.346-42")
    private String documento;

	@ApiModelProperty(notes = "E-mail de contato", required = true, example = "teste@test.com.br")
	private String email;

	@Enumerated(EnumType.STRING)
	@ApiModelProperty(notes = "Situação atual do recurso", required = true, example = "ATIVO")
	private Situacao situacao;

	@ApiModelProperty(notes = "Comissão do prestador de serviço", required = true, example = "25")
	private Integer comissao;
	
	@ApiModelProperty(notes = "Telefone de contato", required = true)
	private TelefoneResponseDTO telefone;

	@ApiModelProperty(notes = "Endereço de contato", required = true)
    private EnderecoResponseDTO endereco;
}
