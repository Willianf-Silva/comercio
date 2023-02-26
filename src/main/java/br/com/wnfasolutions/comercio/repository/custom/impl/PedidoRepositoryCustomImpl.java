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
import br.com.wnfasolutions.comercio.entity.PedidoDO;
import br.com.wnfasolutions.comercio.entity.SituacaoPedidoDO;
import br.com.wnfasolutions.comercio.repository.custom.PedidoRepositoryCustom;
import br.com.wnfasolutions.comercio.repository.filtro.PedidoFiltro;

public class PedidoRepositoryCustomImpl implements PedidoRepositoryCustom {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<PedidoDO> buscarPedidos(PedidoFiltro pedidoFiltro,
			Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<PedidoDO> criteria = builder.createQuery(PedidoDO.class);
		Root<PedidoDO> root = criteria.from(PedidoDO.class);

		Predicate[] predicates = criarRestricoes(pedidoFiltro, builder, root);
		criteria.where(predicates);// .groupBy(root.get("nome"));

		TypedQuery<PedidoDO> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(pedidoFiltro));
	}

	private Predicate[] criarRestricoes(PedidoFiltro pedidoFiltro, CriteriaBuilder builder,
			Root<PedidoDO> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!ObjectUtils.isEmpty(pedidoFiltro.getDocumentoCliente())) {
			Join<PedidoDO, OrcamentoDO> joinOrcamento = root.join("orcamento");
			Join<OrcamentoDO, ClienteDO> joinCliente = joinOrcamento.join("cliente");
			Path<String> campoDocumento = joinCliente.get("documento");
			predicates.add(builder.isTrue(campoDocumento.in(pedidoFiltro.getDocumentoCliente())
					));
		}
		if (!ObjectUtils.isEmpty(pedidoFiltro.getNomeCliente())) {
			Join<PedidoDO, OrcamentoDO> joinOrcamento = root.join("orcamento");
			Join<OrcamentoDO, ClienteDO> joinCliente = joinOrcamento.join("cliente");
			Path<String> campoNome = joinCliente.get("nome");
			predicates.add(builder.isTrue(campoNome.in(
					pedidoFiltro.getNomeCliente()
					)));
		}
		if (!ObjectUtils.isEmpty(pedidoFiltro.getUsernameUsuario())) {
			Join<PedidoDO, OrcamentoDO> joinOrcamento = root.join("orcamento");
			Join<OrcamentoDO, ClienteDO> joinCliente = joinOrcamento.join("usuario");
			Path<String> campoUsername = joinCliente.get("username");
			predicates.add(builder.isTrue(campoUsername.in(
					pedidoFiltro.getUsernameUsuario()
					)));
		}
		if (!ObjectUtils.isEmpty(pedidoFiltro.getDataInclusaoInicio())) {
			if (!ObjectUtils.isEmpty(pedidoFiltro.getDataInclusaoFim())) {
				predicates.add(builder.between(root.get("dataInclusao"),
						LocalDateTime.of(pedidoFiltro.getDataInclusaoInicio(), LocalTime.MIN),
						LocalDateTime.of(pedidoFiltro.getDataInclusaoFim(), LocalTime.MAX)
						));				
			}
		}
		if (!ObjectUtils.isEmpty(pedidoFiltro.getSituacaoPedidoFiltro())) {
			Class<?> situacaoPedido = pedidoFiltro.getSituacaoPedidoFiltro().convertToClass();
			Join<PedidoDO, SituacaoPedidoDO> joinSituacaoPedido = root.join("situacaoPedido");
			predicates.add(builder.equal(joinSituacaoPedido.type(), situacaoPedido));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private Long total(PedidoFiltro pedidoFiltro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<PedidoDO> root = criteria.from(PedidoDO.class);

		Predicate[] predicates = criarRestricoes(pedidoFiltro, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<PedidoDO> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegstroPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegstroPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegstroPorPagina);
	}

}
