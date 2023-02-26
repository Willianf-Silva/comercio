package br.com.wnfasolutions.comercio.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.wnfasolutions.comercio.entity.PedidoDO;
import br.com.wnfasolutions.comercio.repository.filtro.PedidoFiltro;

@Repository
public interface PedidoRepositoryCustom {
	Page<PedidoDO> buscarPedidos(PedidoFiltro pedidoFiltro, Pageable pageable);
}
