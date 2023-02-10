package br.com.wnfasolutions.comercio.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.wnfasolutions.comercio.entity.PrestadorDO;
import br.com.wnfasolutions.comercio.repository.filtro.PrestadorFiltro;

@Repository
public interface PrestadorRepositoryCustom {
	Page<PrestadorDO> buscarPrestadores(PrestadorFiltro prestadorFiltro, Pageable pageable);
}