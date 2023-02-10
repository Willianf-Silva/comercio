package br.com.wnfasolutions.comercio.repository.filtro;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.wnfasolutions.comercio.enuns.Situacao;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteFiltro {

	@ApiModelProperty(notes = "Nome do cliente", required = false, example = "Willian")
	private String nome;

	@ApiModelProperty(notes = "Documento do cliente", required = false, example = "772.324.346-42")
	private String documento;
	
	@ApiModelProperty(notes = "Inscrição estadual do cliente", required = false, example = "074.268.647.718")
	private String inscricaoEstadual;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(notes = "Data de nascimento", required = false, example = "15/05/1991")
	private LocalDate dataNascimentoInicio;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(notes = "Data de nascimento", required = false, example = "15/05/1991")
	private LocalDate dataNascimentoFim;
	
	@ApiModelProperty(notes = "E-mail de contato", required = false, example = "teste@test.com.br")
	private String email;

	@ApiModelProperty(notes = "Situação atual do recurso", required = false, example = "ATIVO")
	private Situacao situacao;
}
