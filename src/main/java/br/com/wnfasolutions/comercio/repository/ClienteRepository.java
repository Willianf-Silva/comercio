package br.com.wnfasolutions.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.wnfasolutions.comercio.entity.ClienteDO;
import br.com.wnfasolutions.comercio.repository.custom.ClienteRepositoryCustom;

@EnableJpaRepositories
public interface ClienteRepository extends JpaRepository<ClienteDO, Long>, ClienteRepositoryCustom{

}
