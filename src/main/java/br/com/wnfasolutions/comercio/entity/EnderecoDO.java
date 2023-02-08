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
@Table(name = "tb_endereco")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String endereco;

	@Column(nullable = false)
	private String bairro;

	@Column(nullable = false)
	private String cep;

	@Column(nullable = true)
	private String complemento;

	@Column(nullable = false)
	private String cidade;

	@Column(nullable = false)
	private String estado;
}
