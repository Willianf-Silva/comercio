package br.com.wnfasolutions.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wnfasolutions.comercio.entity.UsuarioDO;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioDO, Long>{

}
