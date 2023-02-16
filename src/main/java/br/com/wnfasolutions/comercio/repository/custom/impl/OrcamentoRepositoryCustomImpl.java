package br.com.wnfasolutions.comercio.repository.custom.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import br.com.wnfasolutions.comercio.entity.ClienteDO;
import br.com.wnfasolutions.comercio.entity.OrcamentoDO;
import br.com.wnfasolutions.comercio.entity.UsuarioDO;
import br.com.wnfasolutions.comercio.repository.custom.OrcamentoRepositoryCustom;
import br.com.wnfasolutions.comercio.repository.filtro.OrcamentoFiltro;

public class OrcamentoRepositoryCustomImpl implements OrcamentoRepositoryCustom {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<OrcamentoDO> buscarOrcamentos(OrcamentoFiltro orcamentoFiltro,
			Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<OrcamentoDO> criteria = builder.createQuery(OrcamentoDO.class);
		Root<OrcamentoDO> root = criteria.from(OrcamentoDO.class);

		Predicate[] predicates = criarRestricoes(orcamentoFiltro, builder, root);
		criteria.where(predicates);// .groupBy(root.get("nome"));

		TypedQuery<OrcamentoDO> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(orcamentoFiltro));
	}

	private Predicate[] criarRestricoes(OrcamentoFiltro orcamentoFiltro, CriteriaBuilder builder,
			Root<OrcamentoDO> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!ObjectUtils.isEmpty(orcamentoFiltro.getDocumentoCliente())) {
			Join<OrcamentoDO, ClienteDO> join = root.join("cliente");
			Path<String> campoDocumento = join.get("documento");
			predicates.add(builder.isTrue(campoDocumento.in(orcamentoFiltro.getDocumentoCliente())
					));
		}
		if (!ObjectUtils.isEmpty(orcamentoFiltro.getNomeCliente())) {
			Join<OrcamentoDO, ClienteDO> join = root.join("cliente");
			Path<String> campoNome = join.get("nome");
			predicates.add(builder.isTrue(campoNome.in(
					orcamentoFiltro.getNomeCliente()
					)));
		}
		if (!ObjectUtils.isEmpty(orcamentoFiltro.getUsernameUsuario())) {
			Join<OrcamentoDO, UsuarioDO> join = root.join("usuario");
			Path<String> campoUsername = join.get("username");
			predicates.add(builder.isTrue(campoUsername.in(
					orcamentoFiltro.getUsernameUsuario()
					)));
		}
		if (!ObjectUtils.isEmpty(orcamentoFiltro.getDataInclusaoInicio())) {
			if (!ObjectUtils.isEmpty(orcamentoFiltro.getDataInclusaoFim())) {
				predicates.add(builder.between(root.get("dataInclusao"),
						LocalDateTime.of(orcamentoFiltro.getDataInclusaoInicio(), LocalTime.MIN),
						LocalDateTime.of(orcamentoFiltro.getDataInclusaoFim(), LocalTime.MAX)
						));				
			}
		}
		if (!ObjectUtils.isEmpty(orcamentoFiltro.getStatusOrcamento())) {
			predicates.add(builder.equal(builder.upper(root.get("statusOrcamento")),
					orcamentoFiltro.getStatusOrcamento()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private Long total(OrcamentoFiltro orcamentoFiltro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<OrcamentoDO> root = criteria.from(OrcamentoDO.class);

		Predicate[] predicates = criarRestricoes(orcamentoFiltro, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<OrcamentoDO> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegstroPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegstroPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegstroPorPagina);
	}

}
