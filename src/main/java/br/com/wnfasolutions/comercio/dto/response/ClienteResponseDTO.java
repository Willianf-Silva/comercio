package br.com.wnfasolutions.comercio.dto.response;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class ClienteResponseDTO {

	@ApiModelProperty(notes = "Identificador único", required = true, example = "01")
	private Long id;

	@ApiModelProperty(notes = "Nome do cliente", required = true, example = "Willian")
	private String nome;

	@ApiModelProperty(notes = "CPF do cliente", required = true, example = "772.324.346-42")
	private String cpf;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(notes = "Data de nascimento", required = true, example = "15/05/1991")
	private LocalDate dataNascimento;

	@ApiModelProperty(notes = "E-mail de contato", required = true, example = "teste@test.com.br")
	private String email;

	@Enumerated(EnumType.STRING)
	@ApiModelProperty(notes = "Situação atual do recurso", required = true, example = "ATIVO")
	private Situacao situacao;

	@ApiModelProperty(notes = "Apelido do cliente", required = false, example = "Will")
	private String apelido;
}
