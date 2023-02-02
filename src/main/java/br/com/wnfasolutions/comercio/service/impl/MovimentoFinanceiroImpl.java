package br.com.wnfasolutions.comercio.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wnfasolutions.comercio.dto.request.MovimentoFinanceiroRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.MovimentoFinanceiroResponseDTO;
import br.com.wnfasolutions.comercio.entity.MovimentoFinanceiroDO;
import br.com.wnfasolutions.comercio.enuns.Situacao;
import br.com.wnfasolutions.comercio.exception.ResourceNotFoundException;
import br.com.wnfasolutions.comercio.mapper.MovimentoFinanceiroMapper;
import br.com.wnfasolutions.comercio.repository.MovimentoFinanceiroRepository;
import br.com.wnfasolutions.comercio.service.MovimentoFinanceiroService;

@Service
public class MovimentoFinanceiroImpl implements MovimentoFinanceiroService {

	private final MovimentoFinanceiroMapper movimentoFinanceiroMapper = MovimentoFinanceiroMapper.INSTANCE;

	@Autowired
	private MovimentoFinanceiroRepository movimentoFinanceiroRepository;

	@Override
	public MovimentoFinanceiroResponseDTO cadastrarMovimentoFinanceiro(
			MovimentoFinanceiroRequestDTO movimentoFinanceiroRequestDTO) throws Exception {
		MovimentoFinanceiroDO movimentoFinanceiroDO = convertToModel(movimentoFinanceiroRequestDTO);
		movimentoFinanceiroDO.setDataInclusao(LocalDate.now());
		movimentoFinanceiroDO.setSituacao(Situacao.ATIVO);
		MovimentoFinanceiroDO movimentoFinanceiroSalvo = movimentoFinanceiroRepository.save(movimentoFinanceiroDO);
		return convertToResponse(movimentoFinanceiroSalvo);
	}

	@Override
	public MovimentoFinanceiroResponseDTO atualizarMovimentoFinanceiro(Long id,
			MovimentoFinanceiroRequestDTO movimentoFinanceiroRequestDTO) throws Exception {
		MovimentoFinanceiroDO movimentoFinanceiroDO = verificarSeExiste(id);
		movimentoFinanceiroDO.setDataAtualizacao(LocalDate.now());
		BeanUtils.copyProperties(movimentoFinanceiroRequestDTO, movimentoFinanceiroDO, "id");
		MovimentoFinanceiroDO movimentoFinanceiroSalvo = movimentoFinanceiroRepository.save(movimentoFinanceiroDO);
		return convertToResponse(movimentoFinanceiroSalvo);
	}

	@Override
	public MovimentoFinanceiroResponseDTO buscarPorId(Long id) throws Exception {
		MovimentoFinanceiroDO movimentoFinanceiroDO = verificarSeExiste(id);
		return convertToResponse(movimentoFinanceiroDO);
	}

	@Override
	public void inativarMovimentoFinanceiro(Long id) throws Exception {
		alterarSituacaoMovimentoFinanceiro(id, Situacao.INATIVO);
	}

	@Override
	public void ativarMovimentoFinanceiro(Long id) throws Exception {
		alterarSituacaoMovimentoFinanceiro(id, Situacao.ATIVO);
	}

	private void alterarSituacaoMovimentoFinanceiro(Long id, Situacao situacao) throws Exception {
		MovimentoFinanceiroDO movimentoFinanceiroDO = verificarSeExiste(id);
		movimentoFinanceiroDO.setSituacao(situacao);
		movimentoFinanceiroRepository.save(movimentoFinanceiroDO);
	}

	private MovimentoFinanceiroDO verificarSeExiste(Long id) throws ResourceNotFoundException {
		Optional<MovimentoFinanceiroDO> movimentoFinanceiroOptional = movimentoFinanceiroRepository.findById(id);
		if (movimentoFinanceiroOptional.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return movimentoFinanceiroOptional.get();
	}

	private MovimentoFinanceiroResponseDTO convertToResponse(MovimentoFinanceiroDO movimentoFinanceiroDO) {
		return movimentoFinanceiroMapper.toResponseDTO(movimentoFinanceiroDO);
	}

	private MovimentoFinanceiroDO convertToModel(MovimentoFinanceiroRequestDTO movimentoFinanceiroRequestDTO) {
		return movimentoFinanceiroMapper.toModel(movimentoFinanceiroRequestDTO);
	}
}