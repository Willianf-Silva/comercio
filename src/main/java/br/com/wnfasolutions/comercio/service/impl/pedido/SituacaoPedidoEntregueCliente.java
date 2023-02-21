package br.com.wnfasolutions.comercio.service.impl.pedido;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.com.wnfasolutions.comercio.entity.PedidoDO;
import br.com.wnfasolutions.comercio.entity.SituacaoPedidoDO;
import br.com.wnfasolutions.comercio.exception.DomainException;

@Entity
@DiscriminatorValue("Entregue ao Cliente")
public class SituacaoPedidoEntregueCliente extends SituacaoPedidoDO {

	@Override
	public void entregueCliente(PedidoDO pedidoDO) {
		throw new DomainException("Pedido já está entregue ao cliente!");
	}
}
