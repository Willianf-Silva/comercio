package br.com.wnfasolutions.comercio.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wnfasolutions.comercio.dto.request.ClienteRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.ClienteResponseDTO;
import br.com.wnfasolutions.comercio.entity.ClienteDO;
import br.com.wnfasolutions.comercio.enuns.Situacao;
import br.com.wnfasolutions.comercio.exception.ResourceNotFoundException;
import br.com.wnfasolutions.comercio.mapper.ClienteMapper;
import br.com.wnfasolutions.comercio.repository.ClienteRepository;
import br.com.wnfasolutions.comercio.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	private final ClienteMapper clienteMapper = ClienteMapper.INSTANCE;

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public ClienteResponseDTO cadastrarCliente(ClienteRequestDTO clienteRequestDTO) throws Exception {
		ClienteDO clienteDO = convertToModel(clienteRequestDTO);
		clienteDO.setSituacao(Situacao.ATIVO);
		ClienteDO clienteSalvo = clienteRepository.save(clienteDO);
		return convertToResponse(clienteSalvo);
	}

	@Override
	public ClienteResponseDTO atualizarCliente(Long id, ClienteRequestDTO clienteRequestDTO) throws Exception {
		ClienteDO clienteDO = verificarSeExiste(id);
		BeanUtils.copyProperties(clienteRequestDTO, clienteDO, "id");
		ClienteDO clienteSalvo = clienteRepository.save(clienteDO);
		return convertToResponse(clienteSalvo);
	}

	@Override
	public ClienteResponseDTO buscarPorId(Long id) throws Exception {
		ClienteDO clienteDO = verificarSeExiste(id);
		return convertToResponse(clienteDO);
	}

	@Override
	public void inativarCliente(Long id) throws Exception {
		alterarSituacaoCliente(id, Situacao.INATIVO);
	}

	@Override
	public void ativarCliente(Long id) throws Exception {
		alterarSituacaoCliente(id, Situacao.ATIVO);
	}

	private void alterarSituacaoCliente(Long id, Situacao situacao) throws Exception {
		ClienteDO clienteDO = verificarSeExiste(id);
		clienteDO.setSituacao(situacao);
		clienteRepository.save(clienteDO);
	}

	private ClienteDO verificarSeExiste(Long id) throws ResourceNotFoundException {
		Optional<ClienteDO> clienteOptional = clienteRepository.findById(id);
		if (clienteOptional.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return clienteOptional.get();
	}

	private ClienteResponseDTO convertToResponse(ClienteDO clienteDO) {
		return clienteMapper.toResponseDTO(clienteDO);
	}

	private ClienteDO convertToModel(ClienteRequestDTO clienteRequestDTO) {
		return clienteMapper.toModel(clienteRequestDTO);
	}
}
