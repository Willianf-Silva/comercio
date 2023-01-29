package br.com.wnfasolutions.comercio.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Situacao {
	ATIVO("Ativo"),
	INATIVO("Inativo");

	private String descricao;
}