package br.com.wnfasolutions.comercio.dto.request;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

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
public class UsuarioRequestDTO {

	@NotNull
	@ApiModelProperty(notes = "Nome do usuário", required = true, example = "Paulo")
	private String nome;

	@CPF
	@ApiModelProperty(notes = "CPF", required = true, example = "772.324.346-42")
	private String cpf;

	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(notes = "Data de nascimento", required = true, example = "15/05/1991")
	private LocalDate nascimento;

	@NotNull
	@Email
	@ApiModelProperty(notes = "E-mail", required = true, example = "teste@test.com.br")
	private String email;

	@ApiModelProperty(notes = "Usuário de acesso", required = true, example = "will123")
	@NotBlank
	private String username;

	@ApiModelProperty(notes = "Senha de acesso", required = true, example = "admin")
	@NotBlank
	private String password;

	@ApiModelProperty(notes = "Roles para autorização de acesso", required = true)
	@NotNull
	private Set<RoleRequestDTO> roles;
}
