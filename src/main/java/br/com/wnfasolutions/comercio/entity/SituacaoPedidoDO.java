package br.com.wnfasolutions.comercio.entity;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import br.com.wnfasolutions.comercio.exception.DomainException;
import br.com.wnfasolutions.comercio.service.impl.pedido.SituacaoPedido;
import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "situacao_pedido_type")
@Table(name = "tb_situacao_pedido")
public abstract class SituacaoPedidoDO implements SituacaoPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Override
	public void aguardandoEnvioProducao(PedidoDO pedidoDO) {
		throw new DomainException("Pedido não pode estar aguardando envio para produção!");
	}

	@Override
	public void emProducao(PedidoDO pedidoDO) {
		throw new DomainException("Pedido não pode ser enviado para produção!");
	}

	@Override
	public void aguardandoRetiradaCliente(PedidoDO pedidoDO) {
		throw new DomainException("Pedido não pode estar aguardando retirada do cliente!");
	}

	@Override
	public void entregueCliente(PedidoDO pedidoDO) {
		throw new DomainException("Pedido não pode ser entregue!");
	}

	@Override
	public void cancelado(PedidoDO pedidoDO) {
		throw new DomainException("Pedido não pode ser cancelado!");
	}

}
