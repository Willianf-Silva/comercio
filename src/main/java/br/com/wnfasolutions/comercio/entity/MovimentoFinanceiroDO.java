package br.com.wnfasolutions.comercio.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.wnfasolutions.comercio.enuns.FormaPagamento;
import br.com.wnfasolutions.comercio.enuns.Situacao;
import br.com.wnfasolutions.comercio.enuns.TipoMovimento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_financeiro")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovimentoFinanceiroDO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDate dataInclusao;

	@Column(nullable = true)
	private LocalDate dataAtualizacao;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoMovimento tipoMovimento;

	@Column(nullable = false)
	private String descricao;

	@Column(nullable = false)
	private BigDecimal valor;

	@Column(nullable = false)
	private LocalDate dataVencimento;

	@Column(nullable = true)
	private LocalDate dataPagamento;

	@Column(nullable = false)
	private String documentoParcela;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private FormaPagamento formaPagamento;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Situacao situacao;

	public Boolean ativo() {
		return getSituacao().equals(Situacao.ATIVO);
	}
}
