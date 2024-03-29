package br.com.wnfasolutions.comercio.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.wnfasolutions.comercio.dto.request.UsuarioRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.UsuarioResponseDTO;
import br.com.wnfasolutions.comercio.entity.RoleDO;
import br.com.wnfasolutions.comercio.entity.UsuarioDO;
import br.com.wnfasolutions.comercio.enuns.Situacao;
import br.com.wnfasolutions.comercio.exception.RecursoNaoEstaAtivoException;
import br.com.wnfasolutions.comercio.exception.ResourceNotFoundException;
import br.com.wnfasolutions.comercio.exception.RolesNotFoundException;
import br.com.wnfasolutions.comercio.mapper.UsuarioMapper;
import br.com.wnfasolutions.comercio.repository.RoleRepository;
import br.com.wnfasolutions.comercio.repository.UsuarioRepository;
import br.com.wnfasolutions.comercio.repository.filtro.UsuarioFiltro;
import br.com.wnfasolutions.comercio.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private final UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO usuarioRequestDTO) throws Exception {
		Set<RoleDO> roles = getRoles(usuarioRequestDTO);
		UsuarioDO usuarioDO = convertToModel(usuarioRequestDTO);
		usuarioDO.setSituacao(Situacao.ATIVO);
		usuarioDO.setRoles(roles);
		usuarioDO.setPassword(passwordEncoder.encode(usuarioRequestDTO.getPassword()));
		UsuarioDO usuarioSalvo = usuarioRepository.save(usuarioDO);
		return convertToResponse(usuarioSalvo);
	}

	@Override
	public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioRequestDTO usuarioRequestDTO) throws Exception {
		UsuarioDO usuarioDO = verificarSeExiste(id);
		usuarioDO.setRoles(getRoles(usuarioRequestDTO));
		BeanUtils.copyProperties(usuarioRequestDTO, usuarioDO, "id");
		usuarioDO.setPassword(passwordEncoder.encode(usuarioRequestDTO.getPassword()));
		UsuarioDO usuarioSalvo = usuarioRepository.save(usuarioDO);
		return convertToResponse(usuarioSalvo);
	}

	@Override
	public UsuarioResponseDTO buscarPorId(Long id) throws Exception {
		UsuarioDO usuarioDO = verificarSeExiste(id);
		return convertToResponse(usuarioDO);
	}

	@Override
	public Page<UsuarioResponseDTO> buscarUsuarios(UsuarioFiltro usuarioFiltro, Pageable pageable) {

		Page<UsuarioDO> usuariosDO = usuarioRepository.buscarUsuarios(usuarioFiltro, pageable);
		
		List<UsuarioResponseDTO> response = 
				usuariosDO.stream()
				.map(this::convertToResponse)
				.collect(Collectors.toList());

		return new PageImpl<>(response, pageable, usuariosDO.getTotalElements());
	}

	@Override
	public void inativarUsuario(Long id) throws Exception {
		alterarSituacaoUsuario(id, Situacao.INATIVO);
	}

	@Override
	public void ativarUsuario(Long id) throws Exception {
		alterarSituacaoUsuario(id, Situacao.ATIVO);		
	}

	@Override
	public UsuarioDO buscarUsuarioAtivoPorId(Long id) throws Exception {
		UsuarioDO usuarioDO = verificarSeExiste(id);
		verificarSeAtivo(usuarioDO);
		return usuarioDO;
	}

	@Override
	public UsuarioResponseDTO findByUsername(String username) throws Exception {
		Optional<UsuarioDO> usuarioOptional = usuarioRepository.findByUsername(username);
		if (usuarioOptional.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return convertToResponse(usuarioOptional.get());
	}

	private void verificarSeAtivo(UsuarioDO usuarioDO) throws Exception {
		if (!usuarioDO.ativo()) {
			throw new RecursoNaoEstaAtivoException();
		}
	}

	private void alterarSituacaoUsuario(Long id, Situacao situacao) throws Exception {
		UsuarioDO usuarioDO = verificarSeExiste(id);
		usuarioDO.setSituacao(situacao);
		usuarioRepository.save(usuarioDO);
	}

	private UsuarioDO verificarSeExiste(Long id) throws ResourceNotFoundException {
		Optional<UsuarioDO> usuarioOptional = usuarioRepository.findById(id);
		if (usuarioOptional.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return usuarioOptional.get();
	}

	private UsuarioResponseDTO convertToResponse(UsuarioDO usuarioDO) {
		return usuarioMapper.toResponseDTO(usuarioDO);
	}

	private UsuarioDO convertToModel(UsuarioRequestDTO usuarioRequestDTO) {
		return usuarioMapper.toModel(usuarioRequestDTO);
	}
	
	private Set<RoleDO> getRoles(UsuarioRequestDTO usuarioRequestDTO) throws Exception {

		Set<RoleDO> roles = usuarioRequestDTO.getRoles().stream()
				.map(roleName -> roleRepository.findByRoleName(roleName.getRoleName()))
				.filter(role -> role != null)
				.collect(Collectors.toSet());
		if (roles.isEmpty()) {
			throw new RolesNotFoundException();
		}
		return roles;
	}
}
