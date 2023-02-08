package br.com.wnfasolutions.comercio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_telefone")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String ddi;

	@Column(nullable = false)
	private String ddd;

	@Column(nullable = true)
	private String celular;

	@Column(nullable = true)
	private String telefone;
}
