package br.com.wnfasolutions.comercio.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusOrcamento {
	EM_ANALISE("Em Análise"),
	APROVADO("Aprovado"),
	REPROVADO("Reprovado"),
	FINALIZADO("Finalizado"),
	CANCELADO("Cancelado");

	private String descricao;
}