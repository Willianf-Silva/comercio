package br.com.wnfasolutions.comercio.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoMovimento {
	RECEITA("Receita"),
	DESPESA("Despesa");

	private String descricao;
}