package br.com.wnfasolutions.comercio.repository.filtro;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.wnfasolutions.comercio.enuns.FormaPagamento;
import br.com.wnfasolutions.comercio.enuns.Situacao;
import br.com.wnfasolutions.comercio.enuns.Status;
import br.com.wnfasolutions.comercio.enuns.TipoMovimento;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimentoFinanceiroFiltro {
	
	@ApiModelProperty(notes = "Tipo do movimento financeiro", required = false, example = "DESPESA")
	private TipoMovimento tipoMovimento;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(notes = "Data de vencimento do movimento financeiro.", required = false, example = "21/07/2049")
	private LocalDate dataVencimentoInicio;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(notes = "Data de vencimento do movimento financeiro.", required = false, example = "21/07/2050")
	private LocalDate dataVencimentoFim;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(notes = "Data de pagamento do movimento financeiro.", required = false, example = "21/07/2049")
	private LocalDate dataPagamentoInicio;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(notes = "Data de pagamento do movimento financeiro.", required = false, example = "21/07/2050")
	private LocalDate dataPagamentoFim;
	
	@ApiModelProperty(notes = "Documento ou Parcela do movimento financeiro", required = false, example = "Comissão O.S 1")
	private String documentoParcela;
	
	@ApiModelProperty(notes = "Forma de pagamento do movimento financeiro", required = false, example = "DINHEIRO")
	private FormaPagamento formaPagamento;
	
	@ApiModelProperty(notes = "Situação atual do movimento financeiro", required = false, example = "ATIVO")
	private Situacao situacao;
	
	@ApiModelProperty(notes = "Status atual do mvimento financeiro", required = false, example = "PENDENTE")
	private Status status;
	
}
