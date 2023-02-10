package br.com.wnfasolutions.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.wnfasolutions.comercio.entity.PrestadorDO;
import br.com.wnfasolutions.comercio.repository.custom.PrestadorRepositoryCustom;

@EnableJpaRepositories
public interface PrestadorRepository extends JpaRepository<PrestadorDO, Long>, PrestadorRepositoryCustom{

}
