package br.com.wnfasolutions.comercio.config.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.wnfasolutions.comercio.entity.UsuarioDO;
import br.com.wnfasolutions.comercio.repository.UsuarioRepository;

@Repository
public class ImplementsUserDetailsService implements UserDetailsService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UsuarioDO> usuarioEntity = usuarioRepository.findByUsername(username);
		if (usuarioEntity.isEmpty())
			throw new UsernameNotFoundException("Usuário e/ou senha incorretos");
		UsuarioDO veterinarianDO = usuarioEntity.get();
		return new User(username, veterinarianDO.getPassword(), getRoles(veterinarianDO));
	}

	private Collection<? extends GrantedAuthority> getRoles(UsuarioDO veterinarianDO) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		veterinarianDO.getRoles()
				.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName().toUpperCase())));
		return authorities;
	}
}