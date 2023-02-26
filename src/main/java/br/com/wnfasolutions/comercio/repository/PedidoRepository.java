package br.com.wnfasolutions.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.wnfasolutions.comercio.entity.PedidoDO;
import br.com.wnfasolutions.comercio.repository.custom.PedidoRepositoryCustom;

@EnableJpaRepositories
public interface PedidoRepository extends JpaRepository<PedidoDO, Long>, PedidoRepositoryCustom{
}
