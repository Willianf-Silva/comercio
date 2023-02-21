package br.com.wnfasolutions.comercio.service.impl.orcamento;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.com.wnfasolutions.comercio.entity.OrcamentoDO;
import br.com.wnfasolutions.comercio.entity.SituacaoOrcamentoDO;
import br.com.wnfasolutions.comercio.exception.DomainException;

@Entity
@DiscriminatorValue("Em Análise")
public class SituacaoOrcamentoEmAnalise extends SituacaoOrcamentoDO {

	@Override
	public void emAnalise(OrcamentoDO orcamentoDO) {
		throw new DomainException("Orçamento já está em análise!");
	}

	@Override
	public void aprovar(OrcamentoDO orcamentoDO) {
		orcamentoDO.setSituacaoOrcamento(new SituacaoOrcamentoAprovado());
	}

	@Override
	public void cancelar(OrcamentoDO orcamentoDO) {
		orcamentoDO.setSituacaoOrcamento(new SituacaoOrcamentoCancelado());
	}

	@Override
	public void reprovar(OrcamentoDO orcamentoDO) {
		orcamentoDO.setSituacaoOrcamento(new SituacaoOrcamentoReprovado());
	}

	@Override
	public void finalizar(OrcamentoDO orcamentoDO) {
		throw new DomainException("Orçamento em análise não pode ser finalizado!");
	}
	
}
