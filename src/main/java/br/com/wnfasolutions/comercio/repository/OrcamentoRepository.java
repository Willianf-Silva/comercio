package br.com.wnfasolutions.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.wnfasolutions.comercio.entity.OrcamentoDO;
import br.com.wnfasolutions.comercio.repository.custom.OrcamentoRepositoryCustom;

@EnableJpaRepositories
public interface OrcamentoRepository extends JpaRepository<OrcamentoDO, Long>, OrcamentoRepositoryCustom {
}
