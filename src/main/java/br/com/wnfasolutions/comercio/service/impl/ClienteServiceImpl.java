package br.com.wnfasolutions.comercio.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.wnfasolutions.comercio.dto.request.ClienteRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.ClienteResponseDTO;
import br.com.wnfasolutions.comercio.entity.ClienteDO;
import br.com.wnfasolutions.comercio.enuns.Situacao;
import br.com.wnfasolutions.comercio.exception.RecursoNaoEstaAtivoException;
import br.com.wnfasolutions.comercio.exception.ResourceNotFoundException;
import br.com.wnfasolutions.comercio.mapper.ClienteMapper;
import br.com.wnfasolutions.comercio.repository.ClienteRepository;
import br.com.wnfasolutions.comercio.repository.filtro.ClienteFiltro;
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
		BeanUtils.copyProperties(clienteRequestDTO.getEndereco(), clienteDO.getEndereco(), "id");
		BeanUtils.copyProperties(clienteRequestDTO.getTelefone(), clienteDO.getTelefone(), "id");
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
	public Page<ClienteResponseDTO> buscarClientes(ClienteFiltro clienteFiltro, Pageable pageable) {

		Page<ClienteDO> clientesDO = clienteRepository.buscarClientes(clienteFiltro, pageable);
		
		List<ClienteResponseDTO> response = 
				clientesDO.stream()
				.map(this::convertToResponse)
				.collect(Collectors.toList());

		return new PageImpl<>(response, pageable, clientesDO.getTotalElements());
	}

	@Override
	public void inativarCliente(Long id) throws Exception {
		alterarSituacaoCliente(id, Situacao.INATIVO);
	}

	@Override
	public void ativarCliente(Long id) throws Exception {
		alterarSituacaoCliente(id, Situacao.ATIVO);
	}

	@Override
	public ClienteDO buscarClienteAtivoPorId(Long id) throws Exception {
		ClienteDO clienteDO = verificarSeExiste(id);
		verificarSeAtivo(clienteDO);
		return clienteDO;
	}

	private void verificarSeAtivo(ClienteDO clienteDO) throws Exception {
		if (!clienteDO.ativo()) {
			throw new RecursoNaoEstaAtivoException();
		}
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
