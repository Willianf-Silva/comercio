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

import br.com.wnfasolutions.comercio.entity.PrestadorDO;
import br.com.wnfasolutions.comercio.repository.custom.PrestadorRepositoryCustom;
import br.com.wnfasolutions.comercio.repository.filtro.PrestadorFiltro;

public class PrestadorRepositoryCustomImpl implements PrestadorRepositoryCustom {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<PrestadorDO> buscarPrestadores(PrestadorFiltro prestadorFiltro,
			Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<PrestadorDO> criteria = builder.createQuery(PrestadorDO.class);
		Root<PrestadorDO> root = criteria.from(PrestadorDO.class);

		Predicate[] predicates = criarRestricoes(prestadorFiltro, builder, root);
		criteria.where(predicates);// .groupBy(root.get("nome"));

		TypedQuery<PrestadorDO> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(prestadorFiltro));
	}

	private Predicate[] criarRestricoes(PrestadorFiltro prestadorFiltro, CriteriaBuilder builder,
			Root<PrestadorDO> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!ObjectUtils.isEmpty(prestadorFiltro.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get("nome")), "%" + prestadorFiltro.getNome().toLowerCase() + "%"
					));
		}
		if (!ObjectUtils.isEmpty(prestadorFiltro.getDocumento())) {
			predicates.add(builder.equal(builder.upper(root.get("documento")),
					prestadorFiltro.getDocumento()));
		}
		if (!ObjectUtils.isEmpty(prestadorFiltro.getEmail())) {
			predicates.add(builder.like(
					builder.lower(root.get("email")), "%" + prestadorFiltro.getEmail().toLowerCase() + "%"
					));
		}
		if (!ObjectUtils.isEmpty(prestadorFiltro.getSituacao())) {
			predicates.add(builder.equal(builder.upper(root.get("situacao")),
					prestadorFiltro.getSituacao()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private Long total(PrestadorFiltro prestadorFiltro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<PrestadorDO> root = criteria.from(PrestadorDO.class);

		Predicate[] predicates = criarRestricoes(prestadorFiltro, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<PrestadorDO> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegstroPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegstroPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegstroPorPagina);
	}

}
