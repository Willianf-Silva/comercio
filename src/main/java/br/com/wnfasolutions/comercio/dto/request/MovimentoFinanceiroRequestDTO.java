package br.com.wnfasolutions.comercio.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.wnfasolutions.comercio.enuns.FormaPagamento;
import br.com.wnfasolutions.comercio.enuns.TipoMovimento;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovimentoFinanceiroRequestDTO {

	@NotNull
	@ApiModelProperty(notes = "Tipo do movimento financeiro", required = true, example = "DESPESA")
	@Enumerated(EnumType.STRING)
	private TipoMovimento tipoMovimento;

	@NotNull
	@ApiModelProperty(notes = "Descrição do movimento financeiro.", required = true, example = "Pagamento fornecedor ABC")
	private String descricao;

	@ApiModelProperty(notes = "Valor do movimento financeiro.", required = true, example = "100.00")
	@DecimalMin("0.0")
	private BigDecimal valor;

	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(notes = "Data de vencimento do movimento financeiro.", required = true, example = "21/07/2050")
	private LocalDate dataVencimento;

	@NotNull
	@ApiModelProperty(notes = "Documento ou Parcela do movimento financeiro", required = true, example = "Comissão O.S 1")
	private String documentoParcela;

	@NotNull
	@ApiModelProperty(notes = "Forma de pagamento do movimento financeiro", required = true, example = "DINHEIRO")
	@Enumerated(EnumType.STRING)
	private FormaPagamento formaPagamento;

}
