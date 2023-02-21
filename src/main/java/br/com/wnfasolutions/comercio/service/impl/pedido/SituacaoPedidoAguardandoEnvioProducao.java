package br.com.wnfasolutions.comercio.service.impl.pedido;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.com.wnfasolutions.comercio.entity.PedidoDO;
import br.com.wnfasolutions.comercio.entity.SituacaoPedidoDO;
import br.com.wnfasolutions.comercio.exception.DomainException;

@Entity
@DiscriminatorValue("Aguardando Envio para Produção")
public class SituacaoPedidoAguardandoEnvioProducao extends SituacaoPedidoDO {

	@Override
	public void aguardandoEnvioProducao(PedidoDO pedidoDO) {
		throw new DomainException("Pedido já está aguardando envio para produção!");
	}

	@Override
	public void emProducao(PedidoDO pedidoDO) {
		pedidoDO.setSituacaoPedido(new SituacaoPedidoEmProducao());
	}

	@Override
	public void aguardandoRetiradaCliente(PedidoDO pedidoDO) {
		throw new DomainException("Pedido aguardando envio para produção não pode ser retirado pelo cliente!");
	}

	@Override
	public void entregueCliente(PedidoDO pedidoDO) {
		throw new DomainException("Pedido aguardando envio para produção não pode ser entregue ao cliente!");
	}

	@Override
	public void cancelado(PedidoDO pedidoDO) {
		pedidoDO.setSituacaoPedido(new SituacaoPedidoCancelado());
	}

}
