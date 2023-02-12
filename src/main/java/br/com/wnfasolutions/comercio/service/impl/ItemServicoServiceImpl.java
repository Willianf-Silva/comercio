package br.com.wnfasolutions.comercio.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wnfasolutions.comercio.dto.request.ItemServicoRequestDTO;
import br.com.wnfasolutions.comercio.dto.request.OrcamentoRequestDTO;
import br.com.wnfasolutions.comercio.entity.ItemServicoDO;
import br.com.wnfasolutions.comercio.repository.ItemServicoRepository;
import br.com.wnfasolutions.comercio.service.ItemServicoService;
import br.com.wnfasolutions.comercio.service.ServicoService;

@Service
public class ItemServicoServiceImpl implements ItemServicoService {

	@Autowired
	private ItemServicoRepository itemServicoRespository;

	@Autowired
	private ServicoService servicoService;

	@Override
	public List<ItemServicoDO> incluirItens(OrcamentoRequestDTO orcamentoRequestDTO) throws Exception {
		List<ItemServicoDO> itensParaSalvar = montarItens(orcamentoRequestDTO);
		List<ItemServicoDO> itensDO = salvarItens(itensParaSalvar);
		return itensDO;
	}

	private List<ItemServicoDO> montarItens(OrcamentoRequestDTO orcamentoRequestDTO) throws Exception {
		List<ItemServicoDO> itensDO = new ArrayList<>();

		for (ItemServicoRequestDTO item : orcamentoRequestDTO.getItensServico()) {
			MathContext mathContext = new MathContext(7, RoundingMode.HALF_EVEN);
			Integer quantidade = item.getQuantidade();
			BigDecimal desconto = new BigDecimal(item.getDesconto()).divide(BigDecimal.valueOf(100), mathContext);
			BigDecimal soma = item.getValorVendido().multiply(new BigDecimal(quantidade, mathContext));
			BigDecimal subtrairDoTotal = soma.multiply(desconto, mathContext);

			ItemServicoDO itemServicoDO = new ItemServicoDO();
			itemServicoDO.setServico(servicoService.buscarServicoAtivoById(item.getServicoId()));
			itemServicoDO.setQuantidade(quantidade);
			itemServicoDO.setDesconto(item.getDesconto());
			itemServicoDO.setValorVendido(item.getValorVendido());
			itemServicoDO.setSoma(soma.subtract(subtrairDoTotal, mathContext));

			itensDO.add(itemServicoDO);
		}

		return itensDO;
	}

	private List<ItemServicoDO> salvarItens(List<ItemServicoDO> itens) {
		return itemServicoRespository.saveAllAndFlush(itens);
	}

}
