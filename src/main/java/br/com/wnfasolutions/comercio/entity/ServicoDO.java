package br.com.wnfasolutions.comercio.entity;

import java.math.BigDecimal;

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
@Table(name = "tb_servico")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServicoDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100, unique = true)
	private String nome;

	@Column(nullable = true)
	private String descricao;

	@Column(nullable = false)
	private BigDecimal valorCusto;

	@Column(nullable = false)
	private BigDecimal valorVenda;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Situacao situacao;

	public Boolean ativo() {
		return getSituacao().equals(Situacao.ATIVO);
	}
}