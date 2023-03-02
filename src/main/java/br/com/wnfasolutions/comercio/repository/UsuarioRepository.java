package br.com.wnfasolutions.comercio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.wnfasolutions.comercio.entity.UsuarioDO;
import br.com.wnfasolutions.comercio.repository.custom.UsuarioRepositoryCustom;

@EnableJpaRepositories
public interface UsuarioRepository extends JpaRepository<UsuarioDO, Long>, UsuarioRepositoryCustom{

	Optional<UsuarioDO> findByUsername(String username);

}
