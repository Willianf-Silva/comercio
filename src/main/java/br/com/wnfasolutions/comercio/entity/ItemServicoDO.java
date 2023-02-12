package br.com.wnfasolutions.comercio.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_item_servico")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemServicoDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	private ServicoDO servico;

	@Column(nullable = false)
	private Integer quantidade;

	@Column(nullable = true)
	private Integer desconto;

	@Column(nullable = false)
	private BigDecimal valorVendido;

	@Column(nullable = false)
	private BigDecimal soma;
}