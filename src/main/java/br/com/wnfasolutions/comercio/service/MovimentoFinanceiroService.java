package br.com.wnfasolutions.comercio.service;

import br.com.wnfasolutions.comercio.dto.request.MovimentoFinanceiroRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.MovimentoFinanceiroResponseDTO;

public interface MovimentoFinanceiroService {

	MovimentoFinanceiroResponseDTO cadastrarMovimentoFinanceiro(MovimentoFinanceiroRequestDTO movimentoFinanceiroRequestDTO) throws Exception;

	MovimentoFinanceiroResponseDTO atualizarMovimentoFinanceiro(Long id, MovimentoFinanceiroRequestDTO movimentoFinanceiroRequestDTO) throws Exception;

	MovimentoFinanceiroResponseDTO buscarPorId(Long id) throws Exception;

	void inativarMovimentoFinanceiro(Long id) throws Exception;

	void ativarMovimentoFinanceiro(Long id) throws Exception;

}
