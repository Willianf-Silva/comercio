package br.com.wnfasolutions.comercio.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.wnfasolutions.comercio.dto.request.ServicoRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.ServicoResponseDTO;
import br.com.wnfasolutions.comercio.entity.ServicoDO;
import br.com.wnfasolutions.comercio.enuns.Situacao;
import br.com.wnfasolutions.comercio.exception.RecursoNaoEstaAtivoException;
import br.com.wnfasolutions.comercio.exception.ResourceNotFoundException;
import br.com.wnfasolutions.comercio.mapper.ServicoMapper;
import br.com.wnfasolutions.comercio.repository.ServicoRepository;
import br.com.wnfasolutions.comercio.repository.filtro.ServicoFiltro;
import br.com.wnfasolutions.comercio.service.ServicoService;

@Service
public class ServicoServiceImpl implements ServicoService {

	private final ServicoMapper servicoMapper = ServicoMapper.INSTANCE;

	@Autowired
	private ServicoRepository servicoRepository;

	@Override
	public ServicoResponseDTO cadastrarServico(ServicoRequestDTO servicoRequestDTO) throws Exception {
		ServicoDO servicoDO = convertToModel(servicoRequestDTO);
		servicoDO.setSituacao(Situacao.ATIVO);
		servicoDO = this.calcularLucro(servicoDO);
		ServicoDO servicoSalvo = servicoRepository.save(servicoDO);
		return convertToResponse(servicoSalvo);
	}

	@Override
	public ServicoResponseDTO atualizarServico(Long id, ServicoRequestDTO servicoRequestDTO) throws Exception {
		ServicoDO servicoDO = verificarSeExiste(id);
		BeanUtils.copyProperties(servicoRequestDTO, servicoDO, "id");
		servicoDO = this.calcularLucro(servicoDO);
		ServicoDO servicoSalvo = servicoRepository.save(servicoDO);
		return convertToResponse(servicoSalvo);
	}

	@Override
	public ServicoResponseDTO buscarPorId(Long id) throws Exception {
		ServicoDO servicoDO = verificarSeExiste(id);
		return convertToResponse(servicoDO);
	}

	@Override
	public Page<ServicoResponseDTO> buscarServicos(ServicoFiltro servicoFiltro, Pageable pageable) {

		Page<ServicoDO> servicosDO = servicoRepository.buscarServicos(servicoFiltro, pageable);
		
		List<ServicoResponseDTO> response = 
				servicosDO.stream()
				.map(this::convertToResponse)
				.collect(Collectors.toList());

		return new PageImpl<>(response, pageable, servicosDO.getTotalElements());
	}

	@Override
	public void inativarServico(Long id) throws Exception {
		alterarSituacaoServico(id, Situacao.INATIVO);
	}

	@Override
	public void ativarServico(Long id) throws Exception {
		alterarSituacaoServico(id, Situacao.ATIVO);
	}

	@Override
	public ServicoDO buscarServicoAtivoById(Long id) throws Exception {
		ServicoDO servicoDO = verificarSeExiste(id);
		verificarStatusAtivo(servicoDO);
		return servicoDO;
	}

	private void verificarStatusAtivo(ServicoDO servicoDO) throws RecursoNaoEstaAtivoException {
		if (!servicoDO.ativo()) {
			throw new RecursoNaoEstaAtivoException();
		}
		
	}

	private void alterarSituacaoServico(Long id, Situacao situacao) throws Exception {
		ServicoDO servicoDO = verificarSeExiste(id);
		servicoDO.setSituacao(situacao);
		servicoRepository.save(servicoDO);
	}

	private ServicoDO verificarSeExiste(Long id) throws ResourceNotFoundException {
		Optional<ServicoDO> servicoOptional = servicoRepository.findById(id);
		if (servicoOptional.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return servicoOptional.get();
	}

	private ServicoDO calcularLucro(ServicoDO servicoDO) {
		MathContext mathContext = new MathContext(5, RoundingMode.HALF_EVEN);
		BigDecimal valorCusto = servicoDO.getValorCusto();
		BigDecimal valorVenda = servicoDO.getValorVenda();
		BigDecimal lucroMonetario = valorVenda.subtract(valorCusto);

		BigDecimal lucroPercentual = lucroMonetario
				.divide(valorCusto, mathContext)
				.multiply(BigDecimal.valueOf(100), mathContext);
		
		servicoDO.setLucroMonetario(lucroMonetario);
		servicoDO.setLucroPercentual(lucroPercentual);
		return servicoDO;
	}

	private ServicoResponseDTO convertToResponse(ServicoDO servicoDO) {
		return servicoMapper.toResponseDTO(servicoDO);
	}

	private ServicoDO convertToModel(ServicoRequestDTO servicoRequestDTO) {
		return servicoMapper.toModel(servicoRequestDTO);
	}
}
