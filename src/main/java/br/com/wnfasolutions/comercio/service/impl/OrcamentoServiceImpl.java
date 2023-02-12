package br.com.wnfasolutions.comercio.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wnfasolutions.comercio.dto.request.OrcamentoRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.OrcamentoResponseDTO;
import br.com.wnfasolutions.comercio.entity.ItemServicoDO;
import br.com.wnfasolutions.comercio.entity.OrcamentoDO;
import br.com.wnfasolutions.comercio.exception.ResourceNotFoundException;
import br.com.wnfasolutions.comercio.mapper.OrcamentoMapper;
import br.com.wnfasolutions.comercio.repository.OrcamentoRepository;
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
		// TODO Cancelar/Reprovar orçamento existente
		OrcamentoDO orcamentoDO = incluirNovoOrcamento(orcamentoRequestDTO);
		return convertToResponse(orcamentoDO);
	}

	private OrcamentoDO incluirNovoOrcamento(OrcamentoRequestDTO orcamentoRequestDTO) throws Exception {
		verificarListaVazia(orcamentoRequestDTO.getItensServico());

		OrcamentoDO orcamentoNovo = new OrcamentoDO();
		orcamentoNovo.setDataInclusao(LocalDate.now());
		orcamentoNovo.setCliente(clienteService.buscarClienteAtivoPorId(orcamentoRequestDTO.getIdCliente()));
		orcamentoNovo.setUsuario(usuarioService.buscarUsuarioAtivoPorId(orcamentoRequestDTO.getIdUsuario()));
		List<ItemServicoDO> itensDO = itemServicoService.incluirItens(orcamentoRequestDTO);
		orcamentoNovo.setItensServico(itensDO);
		orcamentoNovo.setValor(this.somarTotalServicos(itensDO));

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
