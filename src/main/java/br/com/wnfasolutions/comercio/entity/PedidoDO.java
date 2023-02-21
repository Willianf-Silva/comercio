package br.com.wnfasolutions.comercio.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.wnfasolutions.comercio.service.impl.pedido.SituacaoPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_pedido")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDateTime dataInclusao;

	@Column(nullable = true)
	private LocalDateTime dataAlteracao;


	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = SituacaoPedidoDO.class)
	SituacaoPedido situacaoPedido;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orcamento_id")
	OrcamentoDO orcamento;

	
	public void aguardandoEnvioProducao() {
		situacaoPedido.aguardandoEnvioProducao(this);
	}
	
	public void emProducao() {
		situacaoPedido.emProducao(this);
	}
	
	public void aguardandoRetiradaCliente() {
		situacaoPedido.aguardandoRetiradaCliente(this);
	}
	
	public void entregueCliente() {
		situacaoPedido.entregueCliente(this);
	}
	
	public void cancelado() {
		situacaoPedido.cancelado(this);
	}
}
