package br.com.wnfasolutions.comercio.entity;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import br.com.wnfasolutions.comercio.exception.DomainException;
import br.com.wnfasolutions.comercio.service.impl.orcamento.SituacaoOrcamento;
import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "situacao_orcamento_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "tb_situacao_orcamento")
public abstract class SituacaoOrcamentoDO implements SituacaoOrcamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Override
	public void emAnalise(OrcamentoDO orcamentoDO) {
		throw new DomainException("Orçamento não pode ser enviado para análise!");
	}

	@Override
	public void aprovar(OrcamentoDO orcamentoDO) {
		throw new DomainException("Orçamento não pode ser aprovado!");
	}

	@Override
	public void cancelar(OrcamentoDO orcamentoDO) {
		throw new DomainException("Orçamento não pode ser cancelado!");
	}

	@Override
	public void reprovar(OrcamentoDO orcamentoDO) {
		throw new DomainException("Orçamento não pode ser reprovado!");
	}

	@Override
	public void finalizar(OrcamentoDO orcamentoDO) {
		throw new DomainException("Orçamento não pode ser finalizado!");
	}
}
