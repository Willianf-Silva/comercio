package br.com.wnfasolutions.comercio.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
	PENDENTE("Pendente"),
	FINALIZADO("Finalizado");

	private String descricao;
}