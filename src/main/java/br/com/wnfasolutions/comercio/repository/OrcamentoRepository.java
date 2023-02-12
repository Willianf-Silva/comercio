package br.com.wnfasolutions.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wnfasolutions.comercio.entity.OrcamentoDO;

@Repository
public interface OrcamentoRepository extends JpaRepository<OrcamentoDO, Long> {
}
