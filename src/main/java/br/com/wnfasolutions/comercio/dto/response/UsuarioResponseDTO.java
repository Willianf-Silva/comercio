package br.com.wnfasolutions.comercio.dto.response;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

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
public class UsuarioResponseDTO {

	@ApiModelProperty(notes = "Identificador único", required = true, example = "01")
	private Long id;

	@ApiModelProperty(notes = "Nome do usuário", required = true, example = "Paulo")
	private String nome;

	@ApiModelProperty(notes = "CPF", required = true, example = "772.324.346-42")
	private String cpf;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(notes = "Data de nascimento", required = true, example = "15/05/1991")
	private LocalDate nascimento;

	@ApiModelProperty(notes = "E-mail", required = true, example = "teste@test.com.br")
	private String email;

	@Enumerated(EnumType.STRING)
	@ApiModelProperty(notes = "Situação atual do recurso", required = true, example = "ATIVO")
	private Situacao situacao;

	@ApiModelProperty(notes = "Usuário de acesso", required = true, example = "will123")
	private String username;

	@ApiModelProperty(notes = "Roles para autorização de acesso", required = true)
	@NotNull
	private Set<RoleResponseDTO> roles;

}
