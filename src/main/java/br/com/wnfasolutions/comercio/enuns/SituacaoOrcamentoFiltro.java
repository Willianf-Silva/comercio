package br.com.wnfasolutions.comercio.enuns;

import br.com.wnfasolutions.comercio.exception.DomainException;
import br.com.wnfasolutions.comercio.service.impl.orcamento.SituacaoOrcamentoAprovado;
import br.com.wnfasolutions.comercio.service.impl.orcamento.SituacaoOrcamentoCancelado;
import br.com.wnfasolutions.comercio.service.impl.orcamento.SituacaoOrcamentoEmAnalise;
import br.com.wnfasolutions.comercio.service.impl.orcamento.SituacaoOrcamentoFinalizado;
import br.com.wnfasolutions.comercio.service.impl.orcamento.SituacaoOrcamentoReprovado;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SituacaoOrcamentoFiltro {
	ANALISE,
	APROVADO,
	CANCELADO,
	REPROVADO,
	FINALIZADO;

	public Class<?> convertToClass() {
		if (ANALISE.equals(this)) {
			return SituacaoOrcamentoEmAnalise.class;
		}
		if (APROVADO.equals(this)) {
			return SituacaoOrcamentoAprovado.class;
		}
		if (CANCELADO.equals(this)) {
			return SituacaoOrcamentoCancelado.class;
		}
		if (REPROVADO.equals(this)) {
			return SituacaoOrcamentoReprovado.class;
		}
		if (FINALIZADO.equals(this)) {
			return SituacaoOrcamentoFinalizado.class;
		}
		
		throw new DomainException("Não foi informado uma situação válida para o filtro.");
	}

}