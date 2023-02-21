package br.com.wnfasolutions.comercio.service.impl.pedido;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.com.wnfasolutions.comercio.entity.PedidoDO;
import br.com.wnfasolutions.comercio.entity.SituacaoPedidoDO;
import br.com.wnfasolutions.comercio.exception.DomainException;

@Entity
@DiscriminatorValue("Aguardando Retirada Cliente")
public class SituacaoPedidoAguardandoRetiradaCliente extends SituacaoPedidoDO {

	@Override
	public void aguardandoEnvioProducao(PedidoDO pedidoDO) {
		throw new DomainException("Pedido aguardando retirada não pode ser enviado para produção!");
	}

	@Override
	public void emProducao(PedidoDO pedidoDO) {
		throw new DomainException("Pedido aguardando retirada não pode ser enviado para produção!");
	}

	@Override
	public void aguardandoRetiradaCliente(PedidoDO pedidoDO) {
		throw new DomainException("Pedido já está aguardando retirada cliente!");
	}

	@Override
	public void entregueCliente(PedidoDO pedidoDO) {
		pedidoDO.setSituacaoPedido(new SituacaoPedidoEntregueCliente());
	}

}
