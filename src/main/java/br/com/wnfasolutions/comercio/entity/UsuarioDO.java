package br.com.wnfasolutions.comercio.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.wnfasolutions.comercio.enuns.Situacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_usuario")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false, unique = true)
	private String cpf;

	private LocalDate nascimento;

	private String email;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Situacao situacao;

	@NotNull
	@Column(unique = true)
	private String username;

	@NotNull
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_usuario_role", // nome da tabela que realiza o relacionamento entre user e role
			joinColumns = @JoinColumn(name = "usuario_id"), // chave primaria da tabela atual
			inverseJoinColumns = @JoinColumn(name = "tb_role_id") // chave primaria da outra tabrela
	)
	private Set<RoleDO> roles; // Foi utilizado Set para forçar que o usuário não tenha repetição nas roles

	public Boolean ativo() {
		return getSituacao().equals(Situacao.ATIVO);
	}
}
