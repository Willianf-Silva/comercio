package br.com.wnfasolutions.comercio.service.impl;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wnfasolutions.comercio.dto.request.PedidoRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.OrcamentoResponseDTO;
import br.com.wnfasolutions.comercio.dto.response.PedidoResponseDTO;
import br.com.wnfasolutions.comercio.entity.OrcamentoDO;
import br.com.wnfasolutions.comercio.entity.PedidoDO;
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

	private PedidoResponseDTO convertToResponse(PedidoDO pedidoDO) {
		PedidoResponseDTO responseDTO = pedidoMapper.toResponseDTO(pedidoDO);
		return responseDTO;
	}

	private LocalDateTime obterDataHoraAtual() {
		return LocalDateTime.now();
	}

	private OrcamentoDO obterOrcamentoDO(PedidoRequestDTO pedidoRequestDTO) throws Exception {
		return orcamentoService.buscarOrcamentoEmAnaliseById(pedidoRequestDTO.getIdOrcamento());
	}

	private OrcamentoResponseDTO aprovarOrcamentoDO(PedidoRequestDTO pedidoRequestDTO) throws Exception {
		return orcamentoService.aprovarOrcamento(pedidoRequestDTO.getIdOrcamento());
	}

}
