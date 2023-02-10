package br.com.wnfasolutions.comercio.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.wnfasolutions.comercio.entity.UsuarioDO;
import br.com.wnfasolutions.comercio.repository.filtro.UsuarioFiltro;

@Repository
public interface UsuarioRepositoryCustom {
	Page<UsuarioDO> buscarUsuarios(UsuarioFiltro usuarioFiltro, Pageable pageable);
}
