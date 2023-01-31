package br.com.wnfasolutions.comercio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.wnfasolutions.comercio.enuns.Situacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_prestador")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrestadorDO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false, unique = true)
	private String documento;

	private String email;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Situacao situacao;

	@Column(nullable = false)
	private Integer comissao;

//	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
//    private List<Phone> phones;

//	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
//    private List<Address> address;

	public Boolean ativo() {
		return getSituacao().equals(Situacao.ATIVO);
	}
}