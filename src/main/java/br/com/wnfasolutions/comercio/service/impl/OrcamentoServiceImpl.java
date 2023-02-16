package br.com.wnfasolutions.comercio.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.wnfasolutions.comercio.dto.request.OrcamentoRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.OrcamentoResponseDTO;
import br.com.wnfasolutions.comercio.entity.ItemServicoDO;
import br.com.wnfasolutions.comercio.entity.OrcamentoDO;
import br.com.wnfasolutions.comercio.exception.ResourceNotFoundException;
import br.com.wnfasolutions.comercio.mapper.OrcamentoMapper;
import br.com.wnfasolutions.comercio.repository.OrcamentoRepository;
import br.com.wnfasolutions.comercio.repository.filtro.OrcamentoFiltro;
import br.com.wnfasolutions.comercio.service.ClienteService;
import br.com.wnfasolutions.comercio.service.ItemServicoService;
import br.com.wnfasolutions.comercio.service.OrcamentoService;
import br.com.wnfasolutions.comercio.service.UsuarioService;

@Service
public class OrcamentoServiceImpl implements OrcamentoService {

	private final OrcamentoMapper orcamentoMapper = OrcamentoMapper.INSTANCE;

	@Autowired
	private OrcamentoRepository orcamentoRepository;

	@Autowired
	private ItemServicoService itemServicoService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private UsuarioService usuarioService;

	@Override
	@Transactional
	public OrcamentoResponseDTO cadastrarOrcamento(OrcamentoRequestDTO orcamentoRequestDTO) throws Exception {
		OrcamentoDO orcamentoDO = incluirNovoOrcamento(orcamentoRequestDTO);
		return convertToResponse(orcamentoDO);
	}

	@Override
	public OrcamentoResponseDTO atualizarOrcamento(Long id, OrcamentoRequestDTO orcamentoRequestDTO) throws Exception {
		OrcamentoDO orcamentoDOExistente = verificarSeExiste(id);
		orcamentoDOExistente.cancelar();
		orcamentoDOExistente.setDataAlteracao(LocalDateTime.now());
		orcamentoRepository.save(orcamentoDOExistente);
		
		OrcamentoDO orcamentoDO = incluirNovoOrcamento(orcamentoRequestDTO);
		return convertToResponse(orcamentoDO);
	}

	@Override
	public Page<OrcamentoResponseDTO> buscarOrcamentos(OrcamentoFiltro orcamentoFiltro, Pageable pageable) {

		Page<OrcamentoDO> orcamentosDO = orcamentoRepository.buscarOrcamentos(orcamentoFiltro, pageable);
		
		List<OrcamentoResponseDTO> response = 
				orcamentosDO.stream()
				.map(this::convertToResponse)
				.collect(Collectors.toList());

		return new PageImpl<>(response, pageable, orcamentosDO.getTotalElements());
	}

	@Override
	public OrcamentoResponseDTO reprovarOrcamento(Long id) throws Exception {
		OrcamentoDO orcamentoDO = verificarSeExiste(id);
		orcamentoDO.reprovar();
		orcamentoRepository.save(orcamentoDO);
		return null;
	}

	private OrcamentoDO incluirNovoOrcamento(OrcamentoRequestDTO orcamentoRequestDTO) throws Exception {
		verificarListaVazia(orcamentoRequestDTO.getItensServico());

		OrcamentoDO orcamentoNovo = new OrcamentoDO();
		orcamentoNovo.setDataInclusao(LocalDateTime.now());
		orcamentoNovo.setDataAlteracao(LocalDateTime.now());
		orcamentoNovo.setCliente(clienteService.buscarClienteAtivoPorId(orcamentoRequestDTO.getIdCliente()));
		orcamentoNovo.setUsuario(usuarioService.buscarUsuarioAtivoPorId(orcamentoRequestDTO.getIdUsuario()));
		List<ItemServicoDO> itensDO = itemServicoService.incluirItens(orcamentoRequestDTO);
		orcamentoNovo.setItensServico(itensDO);
		orcamentoNovo.setValor(this.somarTotalServicos(itensDO));
		orcamentoNovo.emAnalise();

		OrcamentoDO orcamentoDO = orcamentoRepository.save(orcamentoNovo);
		return orcamentoDO;
	}

	private BigDecimal somarTotalServicos(List<ItemServicoDO> itens) {
		BigDecimal soma = BigDecimal.ZERO;

		for (ItemServicoDO item : itens) {
			soma = soma.add(item.getSoma());
		}

		return soma;
	}

	private void verificarListaVazia(List<?> itens) throws ResourceNotFoundException {
		if (itens.isEmpty()) {
			throw new ResourceNotFoundException();
		}
	}

	private OrcamentoResponseDTO convertToResponse(OrcamentoDO orcamentoDO) {
		return orcamentoMapper.toResponseDTO(orcamentoDO);
	}

	private OrcamentoDO verificarSeExiste(Long id) throws ResourceNotFoundException {
		Optional<OrcamentoDO> orcamentoOptional = orcamentoRepository.findById(id);
		if (orcamentoOptional.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return orcamentoOptional.get();
	}
}
