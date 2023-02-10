package br.com.wnfasolutions.comercio.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.wnfasolutions.comercio.entity.ClienteDO;
import br.com.wnfasolutions.comercio.repository.filtro.ClienteFiltro;

@Repository
public interface ClienteRepositoryCustom {
	Page<ClienteDO> buscarClientes(ClienteFiltro clienteFiltro, Pageable pageable);
}
