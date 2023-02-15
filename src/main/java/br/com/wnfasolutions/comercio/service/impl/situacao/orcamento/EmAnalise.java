package br.com.wnfasolutions.comercio.service.impl.situacao.orcamento;

import br.com.wnfasolutions.comercio.entity.OrcamentoDO;

public class EmAnalise extends SituacaoOrcamento{
	
	@Override
	public void aprovar(OrcamentoDO orcamentoDO) {
		orcamentoDO.setSituacaoOrcamento(new Aprovado());
	}

	@Override
	public void reprovar(OrcamentoDO orcamentoDO) {
		orcamentoDO.setSituacaoOrcamento(new Reprovado());
	}
}
