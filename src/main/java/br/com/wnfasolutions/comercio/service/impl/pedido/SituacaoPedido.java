package br.com.wnfasolutions.comercio.service.impl.pedido;

import br.com.wnfasolutions.comercio.entity.PedidoDO;

public interface SituacaoPedido {

	void cancelado (PedidoDO pedidoDO);
	void aguardandoEnvioProducao (PedidoDO pedidoDO);
	void emProducao (PedidoDO orcamentoDO);
	void aguardandoRetiradaCliente (PedidoDO pedidoDO);
	void entregueCliente (PedidoDO pedidoDO);
}
