package br.com.wnfasolutions.comercio.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.wnfasolutions.comercio.entity.OrcamentoDO;
import br.com.wnfasolutions.comercio.repository.filtro.OrcamentoFiltro;

@Repository
public interface OrcamentoRepositoryCustom {
	Page<OrcamentoDO> buscarOrcamentos(OrcamentoFiltro orcamentoFiltro, Pageable pageable);
}
