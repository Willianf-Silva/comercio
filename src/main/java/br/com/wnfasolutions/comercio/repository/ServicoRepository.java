package br.com.wnfasolutions.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.wnfasolutions.comercio.entity.ServicoDO;
import br.com.wnfasolutions.comercio.repository.custom.ServicoRepositoryCustom;

@EnableJpaRepositories
public interface ServicoRepository extends JpaRepository<ServicoDO, Long>, ServicoRepositoryCustom{
}
