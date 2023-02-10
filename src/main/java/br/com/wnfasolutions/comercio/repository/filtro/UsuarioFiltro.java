package br.com.wnfasolutions.comercio.repository.filtro;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class UsuarioFiltro {
	
	@ApiModelProperty(notes = "Nome do usuário", required = false, example = "Paulo")
	private String nome;

	@ApiModelProperty(notes = "CPF", required = false, example = "772.324.346-42")
	private String cpf;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(notes = "Data de nascimento", required = false, example = "15/05/1991")
	private LocalDate dataNascimentoInicio;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(notes = "Data de nascimento", required = false, example = "15/05/1992")
	private LocalDate dataNascimentoFim;

	@ApiModelProperty(notes = "E-mail", required = false, example = "teste@test.com.br")
	private String email;

	@ApiModelProperty(notes = "Situação atual do recurso", required = false, example = "ATIVO")
	private Situacao situacao;

	@ApiModelProperty(notes = "Usuário de acesso", required = false, example = "will123")
	private String username;

	@ApiModelProperty(notes = "Role para autorização de acesso", required = false, example = "ROLE_ADMIN")
	private String role;

}
