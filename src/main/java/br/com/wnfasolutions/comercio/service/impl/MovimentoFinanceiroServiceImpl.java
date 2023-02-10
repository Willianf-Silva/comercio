package br.com.wnfasolutions.comercio.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.wnfasolutions.comercio.dto.request.MovimentoFinanceiroRequestDTO;
import br.com.wnfasolutions.comercio.dto.request.MovimentosFinanceiroPagamentoDTO;
import br.com.wnfasolutions.comercio.dto.response.MovimentoFinanceiroResponseDTO;
import br.com.wnfasolutions.comercio.entity.MovimentoFinanceiroDO;
import br.com.wnfasolutions.comercio.enuns.Situacao;
import br.com.wnfasolutions.comercio.enuns.Status;
import br.com.wnfasolutions.comercio.exception.MovimentoFinanceiroFinalizadoException;
import br.com.wnfasolutions.comercio.exception.ResourceNotFoundException;
import br.com.wnfasolutions.comercio.mapper.MovimentoFinanceiroMapper;
import br.com.wnfasolutions.comercio.repository.MovimentoFinanceiroRepository;
import br.com.wnfasolutions.comercio.repository.filtro.MovimentoFinanceiroFiltro;
import br.com.wnfasolutions.comercio.service.MovimentoFinanceiroService;

@Service
public class MovimentoFinanceiroServiceImpl implements MovimentoFinanceiroService {

	private final MovimentoFinanceiroMapper movimentoFinanceiroMapper = MovimentoFinanceiroMapper.INSTANCE;

	@Autowired
	private MovimentoFinanceiroRepository movimentoFinanceiroRepository;

	@Override
	public MovimentoFinanceiroResponseDTO cadastrarMovimentoFinanceiro(
			MovimentoFinanceiroRequestDTO movimentoFinanceiroRequestDTO) throws Exception {
		MovimentoFinanceiroDO movimentoFinanceiroDO = convertToModel(movimentoFinanceiroRequestDTO);
		movimentoFinanceiroDO.setDataInclusao(LocalDate.now());
		movimentoFinanceiroDO.setSituacao(Situacao.ATIVO);
		movimentoFinanceiroDO.setStatus(Status.PENDENTE);
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
	public void pagarMovimentos(List<@Valid MovimentosFinanceiroPagamentoDTO> movimentosFinanceiroPagamentoDTO) throws Exception {
		for (MovimentosFinanceiroPagamentoDTO movimentoPagarDTO : movimentosFinanceiroPagamentoDTO) {
			MovimentoFinanceiroDO movimentoFinanceiroDO = verificarSeExiste(movimentoPagarDTO.getIdMovimentoFinanceiro());
			validarStatusParaPagamento(movimentoFinanceiroDO);
			movimentoFinanceiroDO.setDataPagamento(LocalDate.now());
			movimentoFinanceiroDO.setStatus(Status.FINALIZADO);
			movimentoFinanceiroRepository.save(movimentoFinanceiroDO);
		}
	}

	@Override
	public MovimentoFinanceiroResponseDTO buscarPorId(Long id) throws Exception {
		MovimentoFinanceiroDO movimentoFinanceiroDO = verificarSeExiste(id);
		return convertToResponse(movimentoFinanceiroDO);
	}

	@Override
	public Page<MovimentoFinanceiroResponseDTO> buscarMovimentosFinanceiro(
			MovimentoFinanceiroFiltro movimentoFinanceiroFiltro, Pageable pageable) {
		
		Page<MovimentoFinanceiroDO> movimentosFinanceiroDO = movimentoFinanceiroRepository.buscarMovimentoFinanceiro(movimentoFinanceiroFiltro, pageable);
		
		List<MovimentoFinanceiroResponseDTO> response = 
				movimentosFinanceiroDO.stream()
				.map(this::convertToResponse)
				.collect(Collectors.toList());

		return new PageImpl<>(response, pageable, movimentosFinanceiroDO.getTotalElements());
	}

	@Override
	public void inativarMovimentoFinanceiro(Long id) throws Exception {
		alterarSituacaoMovimentoFinanceiro(id, Situacao.INATIVO);
	}

	@Override
	public void ativarMovimentoFinanceiro(Long id) throws Exception {
		alterarSituacaoMovimentoFinanceiro(id, Situacao.ATIVO);
	}

	private Boolean validarStatusParaPagamento(MovimentoFinanceiroDO movimentoFinanceiroDO) throws Exception {
		if (movimentoFinanceiroDO.isFinalizado()) {
			throw new MovimentoFinanceiroFinalizadoException();
		}
		return true;
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
