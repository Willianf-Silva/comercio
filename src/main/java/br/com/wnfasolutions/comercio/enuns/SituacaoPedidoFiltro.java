package br.com.wnfasolutions.comercio.enuns;

import br.com.wnfasolutions.comercio.exception.DomainException;
import br.com.wnfasolutions.comercio.service.impl.pedido.SituacaoPedidoAguardandoEnvioProducao;
import br.com.wnfasolutions.comercio.service.impl.pedido.SituacaoPedidoAguardandoRetiradaCliente;
import br.com.wnfasolutions.comercio.service.impl.pedido.SituacaoPedidoCancelado;
import br.com.wnfasolutions.comercio.service.impl.pedido.SituacaoPedidoEmProducao;
import br.com.wnfasolutions.comercio.service.impl.pedido.SituacaoPedidoEntregueCliente;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SituacaoPedidoFiltro {
	AGUARDANDO_ENVIO_PRODUCAO,
	PRODUCAO,
	AGUARDANDO_RETIRADA_CLIENTE,
	ENTREGUE,
	CANCELADO;

	public Class<?> convertToClass() {
		if (AGUARDANDO_ENVIO_PRODUCAO.equals(this)) {
			return SituacaoPedidoAguardandoEnvioProducao.class;
		}
		if (PRODUCAO.equals(this)) {
			return SituacaoPedidoEmProducao.class;
		}
		if (AGUARDANDO_RETIRADA_CLIENTE.equals(this)) {
			return SituacaoPedidoAguardandoRetiradaCliente.class;
		}
		if (ENTREGUE.equals(this)) {
			return SituacaoPedidoEntregueCliente.class;
		}
		if (CANCELADO.equals(this)) {
			return SituacaoPedidoCancelado.class;
		}
		
		throw new DomainException("Não foi informado uma situação válida para o filtro.");
	}

}