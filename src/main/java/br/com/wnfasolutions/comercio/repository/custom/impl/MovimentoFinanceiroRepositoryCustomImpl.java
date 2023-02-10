package br.com.wnfasolutions.comercio.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import br.com.wnfasolutions.comercio.entity.MovimentoFinanceiroDO;
import br.com.wnfasolutions.comercio.repository.custom.MovimentoFinanceiroRepositoryCustom;
import br.com.wnfasolutions.comercio.repository.filtro.MovimentoFinanceiroFiltro;

public class MovimentoFinanceiroRepositoryCustomImpl implements MovimentoFinanceiroRepositoryCustom {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<MovimentoFinanceiroDO> buscarMovimentoFinanceiro(MovimentoFinanceiroFiltro movimentoFinanceiroFiltro,
			Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<MovimentoFinanceiroDO> criteria = builder.createQuery(MovimentoFinanceiroDO.class);
		Root<MovimentoFinanceiroDO> root = criteria.from(MovimentoFinanceiroDO.class);

		Predicate[] predicates = criarRestricoes(movimentoFinanceiroFiltro, builder, root);
		criteria.where(predicates);// .groupBy(root.get("nome"));

		TypedQuery<MovimentoFinanceiroDO> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(movimentoFinanceiroFiltro));
	}

	private Predicate[] criarRestricoes(MovimentoFinanceiroFiltro movimentoFinanceiroFiltro, CriteriaBuilder builder,
			Root<MovimentoFinanceiroDO> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!ObjectUtils.isEmpty(movimentoFinanceiroFiltro.getTipoMovimento())) {
			predicates.add(builder.equal(builder.upper(root.get("tipoMovimento")),
					movimentoFinanceiroFiltro.getTipoMovimento()));
		}
		if (!ObjectUtils.isEmpty(movimentoFinanceiroFiltro.getDataVencimentoInicio())) {
			if (!ObjectUtils.isEmpty(movimentoFinanceiroFiltro.getDataVencimentoFim())) {
				predicates.add(builder.between(root.get("dataVencimento"),
						movimentoFinanceiroFiltro.getDataVencimentoInicio(),
						movimentoFinanceiroFiltro.getDataVencimentoFim()
						));				
			}
		}
		if (!ObjectUtils.isEmpty(movimentoFinanceiroFiltro.getDataPagamentoInicio())) {
			if (!ObjectUtils.isEmpty(movimentoFinanceiroFiltro.getDataPagamentoFim())) {
				predicates.add(builder.between(root.get("dataPagamento"),
						movimentoFinanceiroFiltro.getDataPagamentoInicio(),
						movimentoFinanceiroFiltro.getDataPagamentoFim()
						));				
			}
		}
		if (!ObjectUtils.isEmpty(movimentoFinanceiroFiltro.getDocumentoParcela())) {
			predicates.add(builder.like(
					builder.lower(root.get("documentoParcela")), "%" + movimentoFinanceiroFiltro.getDocumentoParcela().toLowerCase() + "%"
					));
		}
		if (!ObjectUtils.isEmpty(movimentoFinanceiroFiltro.getFormaPagamento())) {
			predicates.add(builder.equal(builder.upper(root.get("formaPagamento")),
					movimentoFinanceiroFiltro.getFormaPagamento()));
		}
		if (!ObjectUtils.isEmpty(movimentoFinanceiroFiltro.getSituacao())) {
			predicates.add(builder.equal(builder.upper(root.get("situacao")),
					movimentoFinanceiroFiltro.getSituacao()));
		}
		if (!ObjectUtils.isEmpty(movimentoFinanceiroFiltro.getStatus())) {
			predicates.add(builder.equal(builder.upper(root.get("status")),
					movimentoFinanceiroFiltro.getStatus()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private Long total(MovimentoFinanceiroFiltro movimentoFinanceiroFiltro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<MovimentoFinanceiroDO> root = criteria.from(MovimentoFinanceiroDO.class);

		Predicate[] predicates = criarRestricoes(movimentoFinanceiroFiltro, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<MovimentoFinanceiroDO> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegstroPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegstroPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegstroPorPagina);
	}

}
