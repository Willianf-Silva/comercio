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

import br.com.wnfasolutions.comercio.entity.ServicoDO;
import br.com.wnfasolutions.comercio.repository.custom.ServicoRepositoryCustom;
import br.com.wnfasolutions.comercio.repository.filtro.ServicoFiltro;

public class ServicoRepositoryCustomImpl implements ServicoRepositoryCustom {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<ServicoDO> buscarServicos(ServicoFiltro servicoFiltro,
			Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ServicoDO> criteria = builder.createQuery(ServicoDO.class);
		Root<ServicoDO> root = criteria.from(ServicoDO.class);

		Predicate[] predicates = criarRestricoes(servicoFiltro, builder, root);
		criteria.where(predicates);// .groupBy(root.get("nome"));

		TypedQuery<ServicoDO> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(servicoFiltro));
	}

	private Predicate[] criarRestricoes(ServicoFiltro servicoFiltro, CriteriaBuilder builder,
			Root<ServicoDO> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!ObjectUtils.isEmpty(servicoFiltro.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get("nome")), "%" + servicoFiltro.getNome().toLowerCase() + "%"
					));
		}
		if (!ObjectUtils.isEmpty(servicoFiltro.getDescricao())) {
			predicates.add(builder.like(
					builder.lower(root.get("descricao")), "%" + servicoFiltro.getDescricao().toLowerCase() + "%"
					));
		}
		if (!ObjectUtils.isEmpty(servicoFiltro.getSituacao())) {
			predicates.add(builder.equal(builder.upper(root.get("situacao")),
					servicoFiltro.getSituacao()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private Long total(ServicoFiltro servicoFiltro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<ServicoDO> root = criteria.from(ServicoDO.class);

		Predicate[] predicates = criarRestricoes(servicoFiltro, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<ServicoDO> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegstroPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegstroPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegstroPorPagina);
	}

}
