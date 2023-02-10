package br.com.wnfasolutions.comercio.repository.custom.impl;

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

import br.com.wnfasolutions.comercio.entity.RoleDO;
import br.com.wnfasolutions.comercio.entity.UsuarioDO;
import br.com.wnfasolutions.comercio.repository.custom.UsuarioRepositoryCustom;
import br.com.wnfasolutions.comercio.repository.filtro.UsuarioFiltro;

public class UsuarioRepositoryCustomImpl implements UsuarioRepositoryCustom {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<UsuarioDO> buscarUsuarios(UsuarioFiltro usuarioFiltro,
			Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<UsuarioDO> criteria = builder.createQuery(UsuarioDO.class);
		Root<UsuarioDO> root = criteria.from(UsuarioDO.class);

		Predicate[] predicates = criarRestricoes(usuarioFiltro, builder, root);
		criteria.where(predicates);// .groupBy(root.get("nome"));

		TypedQuery<UsuarioDO> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(usuarioFiltro));
	}

	private Predicate[] criarRestricoes(UsuarioFiltro usuarioFiltro, CriteriaBuilder builder,
			Root<UsuarioDO> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!ObjectUtils.isEmpty(usuarioFiltro.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get("nome")), "%" + usuarioFiltro.getNome().toLowerCase() + "%"
					));
		}
		if (!ObjectUtils.isEmpty(usuarioFiltro.getCpf())) {
			predicates.add(builder.equal(builder.upper(root.get("cpf")),
					usuarioFiltro.getCpf()));
		}
		if (!ObjectUtils.isEmpty(usuarioFiltro.getDataNascimentoInicio())) {
			if (!ObjectUtils.isEmpty(usuarioFiltro.getDataNascimentoFim())) {
				predicates.add(builder.between(root.get("nascimento"),
						usuarioFiltro.getDataNascimentoInicio(),
						usuarioFiltro.getDataNascimentoFim()
						));				
			}
		}
		if (!ObjectUtils.isEmpty(usuarioFiltro.getEmail())) {
			predicates.add(builder.like(
					builder.lower(root.get("email")), "%" + usuarioFiltro.getEmail().toLowerCase() + "%"
					));
		}
		if (!ObjectUtils.isEmpty(usuarioFiltro.getSituacao())) {
			predicates.add(builder.equal(builder.upper(root.get("situacao")),
					usuarioFiltro.getSituacao()));
		}
		if (!ObjectUtils.isEmpty(usuarioFiltro.getUsername())) {
			predicates.add(builder.like(
					builder.lower(root.get("username")), "%" + usuarioFiltro.getUsername().toLowerCase() + "%"
					));
		}
		if (!ObjectUtils.isEmpty(usuarioFiltro.getRole())) {
			Join<UsuarioDO, RoleDO> join = root.join("roles");
			Path<String> campoRoleName = join.get("roleName");
			predicates.add(builder.isTrue(campoRoleName.in(usuarioFiltro.getRole())
					));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private Long total(UsuarioFiltro usuarioFiltro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<UsuarioDO> root = criteria.from(UsuarioDO.class);

		Predicate[] predicates = criarRestricoes(usuarioFiltro, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<UsuarioDO> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegstroPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegstroPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegstroPorPagina);
	}

}
