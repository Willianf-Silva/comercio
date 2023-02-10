package br.com.wnfasolutions.comercio.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.wnfasolutions.comercio.dto.request.MovimentoFinanceiroRequestDTO;
import br.com.wnfasolutions.comercio.dto.request.MovimentosFinanceiroPagamentoDTO;
import br.com.wnfasolutions.comercio.dto.response.MovimentoFinanceiroResponseDTO;
import br.com.wnfasolutions.comercio.repository.filtro.MovimentoFinanceiroFiltro;

public interface MovimentoFinanceiroService {

	MovimentoFinanceiroResponseDTO cadastrarMovimentoFinanceiro(MovimentoFinanceiroRequestDTO movimentoFinanceiroRequestDTO) throws Exception;

	MovimentoFinanceiroResponseDTO atualizarMovimentoFinanceiro(Long id, MovimentoFinanceiroRequestDTO movimentoFinanceiroRequestDTO) throws Exception;

	MovimentoFinanceiroResponseDTO buscarPorId(Long id) throws Exception;

	void inativarMovimentoFinanceiro(Long id) throws Exception;

	void ativarMovimentoFinanceiro(Long id) throws Exception;

	void pagarMovimentos(List<MovimentosFinanceiroPagamentoDTO> movimentosFinanceiroPagamentoDTO) throws Exception;

	Page<MovimentoFinanceiroResponseDTO> buscarMovimentosFinanceiro(MovimentoFinanceiroFiltro movimentoFinanceiroFiltro,
			Pageable pageable);

}
