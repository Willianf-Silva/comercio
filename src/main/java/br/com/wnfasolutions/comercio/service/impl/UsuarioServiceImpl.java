package br.com.wnfasolutions.comercio.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wnfasolutions.comercio.dto.request.UsuarioRequestDTO;
import br.com.wnfasolutions.comercio.dto.response.UsuarioResponseDTO;
import br.com.wnfasolutions.comercio.entity.RoleDO;
import br.com.wnfasolutions.comercio.entity.UsuarioDO;
import br.com.wnfasolutions.comercio.enuns.Situacao;
import br.com.wnfasolutions.comercio.exception.RolesNotFoundException;
import br.com.wnfasolutions.comercio.mapper.UsuarioMapper;
import br.com.wnfasolutions.comercio.repository.RoleRepository;
import br.com.wnfasolutions.comercio.repository.UsuarioRepository;
import br.com.wnfasolutions.comercio.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private final UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO usuarioRequestDTO) throws Exception {
		Set<RoleDO> roles = getRoles(usuarioRequestDTO);
		UsuarioDO usuarioDO = convertToModel(usuarioRequestDTO);
		usuarioDO.setSituacao(Situacao.ATIVO);
		usuarioDO.setRoles(roles);
		UsuarioDO usuarioSalvo = usuarioRepository.save(usuarioDO);
		return convertToResponse(usuarioSalvo);
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
