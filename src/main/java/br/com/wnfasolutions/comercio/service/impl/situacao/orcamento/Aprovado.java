package br.com.wnfasolutions.comercio.service.impl.situacao.orcamento;

import br.com.wnfasolutions.comercio.entity.OrcamentoDO;

public class Aprovado extends SituacaoOrcamento {
	
	@Override
	public void finalizar(OrcamentoDO orcamentoDO) {
		orcamentoDO.setSituacaoOrcamento(new Finalizado());
	}
}
