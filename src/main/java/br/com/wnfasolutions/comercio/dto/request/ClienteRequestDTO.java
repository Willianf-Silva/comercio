package br.com.wnfasolutions.comercio.dto.request;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequestDTO{

	@NotNull
	@ApiModelProperty(notes = "Nome do cliente", required = true, example = "Willian")
	private String nome;
	
	@NotNull
	@ApiModelProperty(notes = "Documento do cliente", required = true, example = "772.324.346-42")
    private String documento;
	
	@ApiModelProperty(notes = "Inscrição estadual do cliente", required = false, example = "074.268.647.718")
	private String inscricaoEstadual;
	
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(notes = "Data de nascimento", required = true, example = "15/05/1991")
	private LocalDate dataNascimento;
	
	@NotNull
	@ApiModelProperty(notes = "Telefone de contato", required = true)
	private TelefoneRequestDTO telefone;

	@NotNull
	@ApiModelProperty(notes = "Endereço de contato", required = true)
    private EnderecoRequestDTO endereco;
	
	@NotNull
	@Email
	@ApiModelProperty(notes = "E-mail de contato", required = false, example = "teste@test.com.br")
	private String email;
	
	@ApiModelProperty(notes = "Apelido do cliente", required = false, example = "Will")
	private String apelido;
}