package br.com.wnfasolutions.comercio.service.impl.pedido;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.com.wnfasolutions.comercio.entity.PedidoDO;
import br.com.wnfasolutions.comercio.entity.SituacaoPedidoDO;
import br.com.wnfasolutions.comercio.exception.DomainException;

@Entity
@DiscriminatorValue("Cancelado")
public class SituacaoPedidoCancelado extends SituacaoPedidoDO {
    
	@Override
	public void cancelado(PedidoDO pedidoDO) {
		throw new DomainException("Pedido já está cancelado!");
	}
}
