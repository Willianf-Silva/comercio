package br.com.wnfasolutions.comercio.service;

import java.util.List;

import br.com.wnfasolutions.comercio.dto.request.OrcamentoRequestDTO;
import br.com.wnfasolutions.comercio.entity.ItemServicoDO;

public interface ItemServicoService {

	List<ItemServicoDO> incluirItens(OrcamentoRequestDTO orcamentoRequestDTO) throws Exception;

}
