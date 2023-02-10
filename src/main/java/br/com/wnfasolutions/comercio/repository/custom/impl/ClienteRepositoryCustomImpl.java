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

import br.com.wnfasolutions.comercio.entity.ClienteDO;
import br.com.wnfasolutions.comercio.repository.custom.ClienteRepositoryCustom;
import br.com.wnfasolutions.comercio.repository.filtro.ClienteFiltro;

public class ClienteRepositoryCustomImpl implements ClienteRepositoryCustom {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<ClienteDO> buscarClientes(ClienteFiltro clienteFiltro,
			Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ClienteDO> criteria = builder.createQuery(ClienteDO.class);
		Root<ClienteDO> root = criteria.from(ClienteDO.class);

		Predicate[] predicates = criarRestricoes(clienteFiltro, builder, root);
		criteria.where(predicates);// .groupBy(root.get("nome"));

		TypedQuery<ClienteDO> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(clienteFiltro));
	}

	private Predicate[] criarRestricoes(ClienteFiltro clienteFiltro, CriteriaBuilder builder,
			Root<ClienteDO> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!ObjectUtils.isEmpty(clienteFiltro.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get("nome")), "%" + clienteFiltro.getNome().toLowerCase() + "%"
					));
		}
		if (!ObjectUtils.isEmpty(clienteFiltro.getDocumento())) {
			predicates.add(builder.equal(builder.upper(root.get("documento")),
					clienteFiltro.getDocumento()));
		}
		if (!ObjectUtils.isEmpty(clienteFiltro.getInscricaoEstadual())) {
			predicates.add(builder.equal(builder.upper(root.get("inscricaoEstadual")),
					clienteFiltro.getInscricaoEstadual()));
		}
		if (!ObjectUtils.isEmpty(clienteFiltro.getDataNascimentoInicio())) {
			if (!ObjectUtils.isEmpty(clienteFiltro.getDataNascimentoFim())) {
				predicates.add(builder.between(root.get("dataNascimento"),
						clienteFiltro.getDataNascimentoInicio(),
						clienteFiltro.getDataNascimentoFim()
						));				
			}
		}
		if (!ObjectUtils.isEmpty(clienteFiltro.getEmail())) {
			predicates.add(builder.like(
					builder.lower(root.get("email")), "%" + clienteFiltro.getEmail().toLowerCase() + "%"
					));
		}
		if (!ObjectUtils.isEmpty(clienteFiltro.getSituacao())) {
			predicates.add(builder.equal(builder.upper(root.get("situacao")),
					clienteFiltro.getSituacao()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private Long total(ClienteFiltro clienteFiltro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<ClienteDO> root = criteria.from(ClienteDO.class);

		Predicate[] predicates = criarRestricoes(clienteFiltro, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<ClienteDO> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegstroPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegstroPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegstroPorPagina);
	}

}
