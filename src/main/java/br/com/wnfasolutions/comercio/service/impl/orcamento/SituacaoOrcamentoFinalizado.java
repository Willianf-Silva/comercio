package br.com.wnfasolutions.comercio.service.impl.orcamento;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.com.wnfasolutions.comercio.entity.OrcamentoDO;
import br.com.wnfasolutions.comercio.entity.SituacaoOrcamentoDO;
import br.com.wnfasolutions.comercio.exception.DomainException;

@Entity
@DiscriminatorValue("Finalizado")
public class SituacaoOrcamentoFinalizado extends SituacaoOrcamentoDO {

	@Override
	public void emAnalise(OrcamentoDO orcamentoDO) {
		throw new DomainException("Orçamento finalizado não pode ser enviado para análise!");
	}

	@Override
	public void aprovar(OrcamentoDO orcamentoDO) {
		throw new DomainException("Orçamento finalizado não pode ser aprovado!");
	}

	@Override
	public void cancelar(OrcamentoDO orcamentoDO) {
		throw new DomainException("Orçamento finalizado não pode ser cancelado!");
	}

	@Override
	public void reprovar(OrcamentoDO orcamentoDO) {
		throw new DomainException("Orçamento finalizado não pode ser reprovado!");
	}

	@Override
	public void finalizar(OrcamentoDO orcamentoDO) {
		throw new DomainException("Orçamento já está finalizado!");
	}
}
