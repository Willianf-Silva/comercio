package br.com.wnfasolutions.comercio.dto.response;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ClienteResponseDTO {

	@ApiModelProperty(notes = "Identificador único", required = true, example = "01")
	private Long id;

	@ApiModelProperty(notes = "Nome do cliente", required = true, example = "Willian")
	private String nome;

	@ApiModelProperty(notes = "Documento do cliente", required = true, example = "772.324.346-42")
	private String documento;
	
	@ApiModelProperty(notes = "Inscrição estadual do cliente", required = false, example = "074.268.647.718")
	private String inscricaoEstadual;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(notes = "Data de nascimento", required = true, example = "15/05/1991")
	private LocalDate dataNascimento;

	@ApiModelProperty(notes = "E-mail de contato", required = false, example = "teste@test.com.br")
	private String email;
	
	@Enumerated(EnumType.STRING)
	@ApiModelProperty(notes = "Situação atual do recurso", required = true, example = "ATIVO")
	private Situacao situacao;
	
	@ApiModelProperty(notes = "Apelido do cliente", required = false, example = "Will")
	private String apelido;

	@ApiModelProperty(notes = "Endereço de contato", required = true)
    private EnderecoResponseDTO endereco;
	
	@ApiModelProperty(notes = "Telefone de contato", required = true)
	private TelefoneResponseDTO telefone;
}
