package br.com.wnfasolutions.comercio.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.wnfasolutions.comercio.entity.ServicoDO;
import br.com.wnfasolutions.comercio.repository.filtro.ServicoFiltro;

@Repository
public interface ServicoRepositoryCustom {
	Page<ServicoDO> buscarServicos(ServicoFiltro servicoFiltro, Pageable pageable);
}
