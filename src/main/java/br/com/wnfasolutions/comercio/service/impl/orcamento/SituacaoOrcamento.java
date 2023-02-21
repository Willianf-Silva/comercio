package br.com.wnfasolutions.comercio.service.impl.orcamento;

import br.com.wnfasolutions.comercio.entity.OrcamentoDO;

public interface SituacaoOrcamento {
	void emAnalise(OrcamentoDO orcamentoDO);
	void aprovar(OrcamentoDO orcamentoDO);
	void cancelar(OrcamentoDO orcamentoDO);
	void reprovar(OrcamentoDO orcamentoDO);
	void finalizar(OrcamentoDO orcamentoDO);
}