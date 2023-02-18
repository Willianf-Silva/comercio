package br.com.wnfasolutions.comercio.service.impl.orcamento;

import org.springframework.stereotype.Component;

import br.com.wnfasolutions.comercio.entity.OrcamentoDO;
import br.com.wnfasolutions.comercio.enuns.StatusOrcamento;

@Component
public class Cancelado implements SituacaoOrcamento {

	@Override
	public void processar(OrcamentoDO orcamentoDO) {
		// TODO Realizar a implementação de ações que ocorrem nesse estado
		orcamentoDO.setStatusOrcamento(getSituacao());
	}

	@Override
	public StatusOrcamento getSituacao() {
		return StatusOrcamento.CANCELADO;
	}

}
