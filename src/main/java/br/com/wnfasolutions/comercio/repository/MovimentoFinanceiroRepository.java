package br.com.wnfasolutions.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.wnfasolutions.comercio.entity.MovimentoFinanceiroDO;
import br.com.wnfasolutions.comercio.repository.custom.MovimentoFinanceiroRepositoryCustom;

@EnableJpaRepositories
public interface MovimentoFinanceiroRepository extends JpaRepository<MovimentoFinanceiroDO, Long>, MovimentoFinanceiroRepositoryCustom {

}
