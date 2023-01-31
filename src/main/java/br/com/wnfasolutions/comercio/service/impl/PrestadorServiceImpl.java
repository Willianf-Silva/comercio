package br.com.wnfasolutions.comercio.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wnfasolutions.comercio.dto.request.PrestadorRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.PrestadorResponseDTO;
import br.com.wnfasolutions.comercio.entity.PrestadorDO;
import br.com.wnfasolutions.comercio.enuns.Situacao;
import br.com.wnfasolutions.comercio.exception.ResourceNotFoundException;
import br.com.wnfasolutions.comercio.mapper.PrestadorMapper;
import br.com.wnfasolutions.comercio.repository.PrestadorRepository;
import br.com.wnfasolutions.comercio.service.PrestadorService;

@Service
public class PrestadorServiceImpl implements PrestadorService {

	private final PrestadorMapper prestadorMapper = PrestadorMapper.INSTANCE;

	@Autowired
	private PrestadorRepository prestadorRepository;

	@Override
	public PrestadorResponseDTO cadastrarPrestador(PrestadorRequestDTO prestadorRequestDTO) throws Exception {
		PrestadorDO prestadorDO = convertToModel(prestadorRequestDTO);
		prestadorDO.setSituacao(Situacao.ATIVO);
		PrestadorDO prestadorSalvo = prestadorRepository.save(prestadorDO);
		return convertToResponse(prestadorSalvo);
	}

	@Override
	public PrestadorResponseDTO atualizarPrestador(Long id, PrestadorRequestDTO prestadorRequestDTO) throws Exception {
		PrestadorDO prestadorDO = verificarSeExiste(id);
		BeanUtils.copyProperties(prestadorRequestDTO, prestadorDO, "id");
		PrestadorDO prestadorSalvo = prestadorRepository.save(prestadorDO);
		return convertToResponse(prestadorSalvo);
	}

	@Override
	public PrestadorResponseDTO buscarPorId(Long id) throws Exception {
		PrestadorDO prestadorDO = verificarSeExiste(id);
		return convertToResponse(prestadorDO);
	}

	@Override
	public void inativarPrestador(Long id) throws Exception {
		alterarSituacaoPrestador(id, Situacao.INATIVO);
	}

	@Override
	public void ativarPrestador(Long id) throws Exception {
		alterarSituacaoPrestador(id, Situacao.ATIVO);
	}

	private void alterarSituacaoPrestador(Long id, Situacao situacao) throws Exception {
		PrestadorDO prestadorDO = verificarSeExiste(id);
		prestadorDO.setSituacao(situacao);
		prestadorRepository.save(prestadorDO);
	}

	private PrestadorDO verificarSeExiste(Long id) throws ResourceNotFoundException {
		Optional<PrestadorDO> prestadorOptional = prestadorRepository.findById(id);
		if (prestadorOptional.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return prestadorOptional.get();
	}

	private PrestadorResponseDTO convertToResponse(PrestadorDO prestadorDO) {
		return prestadorMapper.toResponseDTO(prestadorDO);
	}

	private PrestadorDO convertToModel(PrestadorRequestDTO prestadorRequestDTO) {
		return prestadorMapper.toModel(prestadorRequestDTO);
	}
}
