package br.com.wnfasolutions.comercio.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wnfasolutions.comercio.dto.request.PedidoRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.OrcamentoResponseDTO;
import br.com.wnfasolutions.comercio.dto.response.PedidoResponseDTO;
import br.com.wnfasolutions.comercio.entity.OrcamentoDO;
import br.com.wnfasolutions.comercio.entity.PedidoDO;
import br.com.wnfasolutions.comercio.exception.ResourceNotFoundException;
import br.com.wnfasolutions.comercio.mapper.PedidoMapper;
import br.com.wnfasolutions.comercio.repository.PedidoRepository;
import br.com.wnfasolutions.comercio.service.OrcamentoService;
import br.com.wnfasolutions.comercio.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {
	private final PedidoMapper pedidoMapper = PedidoMapper.INSTANCE;
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private OrcamentoService orcamentoService;
	
	@Override
	@Transactional
	public PedidoResponseDTO cadastrarPedido(PedidoRequestDTO pedidoRequestDTO) throws Exception {
		
		PedidoDO pedidoDO = new PedidoDO();
		pedidoDO.setDataInclusao(obterDataHoraAtual());
		pedidoDO.setDataAlteracao(obterDataHoraAtual());
		pedidoDO.setOrcamento(this.obterOrcamentoDO(pedidoRequestDTO));
		
		this.aprovarOrcamentoDO(pedidoRequestDTO);
		PedidoDO pedidoSaved = pedidoRepository.save(pedidoDO);
		
		return convertToResponse(pedidoSaved);
	}

	@Override
	@Transactional
	public PedidoResponseDTO enviarPedidoProducao(Long id) throws Exception {
		PedidoDO pedidoDO = verificarSeExiste(id);
		pedidoDO.emProducao();
		PedidoDO pedidoSaved = pedidoRepository.save(pedidoDO);
		return convertToResponse(pedidoSaved);
	}

	@Override
	@Transactional
	public PedidoResponseDTO pedidoAguardandoRetirada(Long id) throws Exception {
		PedidoDO pedidoDO = verificarSeExiste(id);
		pedidoDO.aguardandoRetiradaCliente();
		PedidoDO pedidoSaved = pedidoRepository.save(pedidoDO);
		return convertToResponse(pedidoSaved);
	}

	@Override
	@Transactional
	public PedidoResponseDTO pedidoEntregue(Long id) throws Exception {
		PedidoDO pedidoDO = verificarSeExiste(id);
		pedidoDO.entregueCliente();
		PedidoDO pedidoSaved = pedidoRepository.save(pedidoDO);
		this.finalizarOrcamentoDO(pedidoSaved.getOrcamento().getId());
		return convertToResponse(pedidoSaved);
	}

	private OrcamentoResponseDTO finalizarOrcamentoDO(Long id) throws Exception {
		return orcamentoService.finalizarOrcamentoById(id);
	}

	private PedidoResponseDTO convertToResponse(PedidoDO pedidoDO) {
		PedidoResponseDTO responseDTO = pedidoMapper.toResponseDTO(pedidoDO);
		return responseDTO;
	}

	private LocalDateTime obterDataHoraAtual() {
		return LocalDateTime.now();
	}

	private PedidoDO verificarSeExiste(Long id) throws ResourceNotFoundException {
		Optional<PedidoDO> pedidoOptional = pedidoRepository.findById(id);
		if (pedidoOptional.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return pedidoOptional.get();
	}

	private OrcamentoDO obterOrcamentoDO(PedidoRequestDTO pedidoRequestDTO) throws Exception {
		return orcamentoService.buscarOrcamentoEmAnaliseById(pedidoRequestDTO.getIdOrcamento());
	}

	private OrcamentoResponseDTO aprovarOrcamentoDO(PedidoRequestDTO pedidoRequestDTO) throws Exception {
		return orcamentoService.aprovarOrcamento(pedidoRequestDTO.getIdOrcamento());
	}

}
