package br.com.wnfasolutions.comercio.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wnfasolutions.comercio.dto.request.ServicoRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.ServicoResponseDTO;
import br.com.wnfasolutions.comercio.entity.ServicoDO;
import br.com.wnfasolutions.comercio.enuns.Situacao;
import br.com.wnfasolutions.comercio.exception.ResourceNotFoundException;
import br.com.wnfasolutions.comercio.mapper.ServicoMapper;
import br.com.wnfasolutions.comercio.repository.ServicoRepository;
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
		ServicoDO servicoSalvo = servicoRepository.save(servicoDO);
		return convertToResponse(servicoSalvo);
	}

	@Override
	public ServicoResponseDTO atualizarServico(Long id, ServicoRequestDTO servicoRequestDTO) throws Exception {
		ServicoDO servicoDO = verificarSeExiste(id);
		BeanUtils.copyProperties(servicoRequestDTO, servicoDO, "id");
		ServicoDO servicoSalvo = servicoRepository.save(servicoDO);
		return convertToResponse(servicoSalvo);
	}

	@Override
	public ServicoResponseDTO buscarPorId(Long id) throws Exception {
		ServicoDO servicoDO = verificarSeExiste(id);
		return convertToResponse(servicoDO);
	}

	@Override
	public void inativarServico(Long id) throws Exception {
		alterarSituacaoServico(id, Situacao.INATIVO);
	}

	@Override
	public void ativarServico(Long id) throws Exception {
		alterarSituacaoServico(id, Situacao.ATIVO);
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

	private ServicoResponseDTO convertToResponse(ServicoDO servicoDO) {
		return servicoMapper.toResponseDTO(servicoDO);
	}

	private ServicoDO convertToModel(ServicoRequestDTO servicoRequestDTO) {
		return servicoMapper.toModel(servicoRequestDTO);
	}
}
