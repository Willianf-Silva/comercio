package br.com.wnfasolutions.comercio.service.impl.situacao.orcamento;

import br.com.wnfasolutions.comercio.entity.OrcamentoDO;
import br.com.wnfasolutions.comercio.exception.DomainException;

public abstract class SituacaoOrcamento {

	public void aprovar(OrcamentoDO orcamentoDO) {
		throw new DomainException("Orçamento não pode ser aprovado!");
	}

	public void reprovar(OrcamentoDO orcamentoDO) {
		throw new DomainException("Orçamento não pode ser reprovado!");
	}

	public void finalizar(OrcamentoDO orcamentoDO) {
		throw new DomainException("Orçamento não pode ser finalizado!");
	}
}