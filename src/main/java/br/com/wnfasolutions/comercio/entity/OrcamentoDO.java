package br.com.wnfasolutions.comercio.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.wnfasolutions.comercio.enuns.StatusOrcamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_orcamento")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrcamentoDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDateTime dataInclusao;

	@Column(nullable = true)
	private LocalDateTime dataAlteracao;

	@Column(nullable = false)
	private BigDecimal valor;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "orcamento_id")
	private List<ItemServicoDO> itensServico;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente_id")
	private ClienteDO cliente;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id")
	private UsuarioDO usuario;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusOrcamento statusOrcamento;

	public void emAnalise() {
		this.statusOrcamento = StatusOrcamento.EM_ANALISE;
	}

	public void aprovar() {
		this.statusOrcamento = StatusOrcamento.APROVADO;
	}

	public void reprovar() {
		this.statusOrcamento = StatusOrcamento.REPROVADO;
	}

	public void finalizar() {
		this.statusOrcamento = StatusOrcamento.FINALIZADO;
	}

	public void cancelar() {
		this.statusOrcamento = StatusOrcamento.CANCELADO;
	}
}
