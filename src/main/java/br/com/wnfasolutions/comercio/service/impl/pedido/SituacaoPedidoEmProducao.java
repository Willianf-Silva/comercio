package br.com.wnfasolutions.comercio.service.impl.pedido;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.com.wnfasolutions.comercio.entity.PedidoDO;
import br.com.wnfasolutions.comercio.entity.SituacaoPedidoDO;
import br.com.wnfasolutions.comercio.exception.DomainException;

@Entity
@DiscriminatorValue("Em Produção")
public class SituacaoPedidoEmProducao extends SituacaoPedidoDO {

	@Override
	public void aguardandoEnvioProducao(PedidoDO pedidoDO) {
		throw new DomainException("Pedido já está em produção!");
	}

	@Override
	public void emProducao(PedidoDO pedidoDO) {
		throw new DomainException("Pedido já está em produção!");
	}

	@Override
	public void aguardandoRetiradaCliente(PedidoDO pedidoDO) {
		pedidoDO.setSituacaoPedido(new SituacaoPedidoAguardandoRetiradaCliente());
	}

	@Override
	public void entregueCliente(PedidoDO pedidoDO) {
		throw new DomainException("Pedido em produção não pode ser entregue ao cliente!");
	}

}
