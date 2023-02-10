package br.com.wnfasolutions.comercio.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.wnfasolutions.comercio.entity.MovimentoFinanceiroDO;
import br.com.wnfasolutions.comercio.repository.filtro.MovimentoFinanceiroFiltro;

@Repository
public interface MovimentoFinanceiroRepositoryCustom {
	Page<MovimentoFinanceiroDO> buscarMovimentoFinanceiro(MovimentoFinanceiroFiltro movimentoFinanceiroFiltro, Pageable pageable);
}