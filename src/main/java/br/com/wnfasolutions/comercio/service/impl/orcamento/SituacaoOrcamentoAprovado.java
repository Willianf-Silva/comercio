package br.com.wnfasolutions.comercio.service.impl.orcamento;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.com.wnfasolutions.comercio.entity.OrcamentoDO;
import br.com.wnfasolutions.comercio.entity.SituacaoOrcamentoDO;
import br.com.wnfasolutions.comercio.exception.DomainException;

@Entity
@DiscriminatorValue("Aprovado")
public class SituacaoOrcamentoAprovado extends SituacaoOrcamentoDO {

	@Override
	public void emAnalise(OrcamentoDO orcamentoDO) {
		throw new DomainException("Orçamento aprovado não pode ser alterado para em análise!");
	}

	@Override
	public void aprovar(OrcamentoDO orcamentoDO) {
		throw new DomainException("Orçamento já está aprovado!");
	}

	@Override
	public void cancelar(OrcamentoDO orcamentoDO) {
		orcamentoDO.setSituacaoOrcamento(new SituacaoOrcamentoCancelado());
	}

	@Override
	public void reprovar(OrcamentoDO orcamentoDO) {
		throw new DomainException("Orçamento aprovado não pode ser reprovado!");
	}

	@Override
	public void finalizar(OrcamentoDO orcamentoDO) {
		orcamentoDO.setSituacaoOrcamento(new SituacaoOrcamentoFinalizado());
	}
}
