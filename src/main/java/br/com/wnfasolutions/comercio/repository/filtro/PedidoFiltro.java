package br.com.wnfasolutions.comercio.repository.filtro;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.wnfasolutions.comercio.enuns.SituacaoPedidoFiltro;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoFiltro {

	@ApiModelProperty(notes = "Documento do cliente", required = false, example = "772.324.346-42")
	private String documentoCliente;

	@ApiModelProperty(notes = "Nome do cliente", required = false, example = "Willian")
	private String nomeCliente;

	@ApiModelProperty(notes = "Nome de acesso do usuário", required = false, example = "will123")
	private String usernameUsuario;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(notes = "Data de realização do pedido.", required = false, example = "21/07/2049")
	private LocalDate dataInclusaoInicio;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(notes = "Data de realização do pedido.", required = false, example = "21/07/2050")
	private LocalDate dataInclusaoFim;
	
	@ApiModelProperty(notes = "Situação do pedido.", required = false, example = "AGUARDANDO_ENVIO_PRODUCAO")
	@Enumerated(EnumType.STRING)
	private SituacaoPedidoFiltro situacaoPedidoFiltro;
}
