package br.com.wnfasolutions.comercio.service.impl.orcamento;

import br.com.wnfasolutions.comercio.entity.OrcamentoDO;
import br.com.wnfasolutions.comercio.enuns.StatusOrcamento;

public interface SituacaoOrcamento {
	void processar(OrcamentoDO orcamentoDO);
	StatusOrcamento getSituacao();
}