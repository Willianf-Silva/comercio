package br.com.wnfasolutions.comercio.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormaPagamento {
	PIX("Pix"),
	DINHEIRO("Dinheiro"),
	CARTAO("Cartão");

	private String descricao;
}