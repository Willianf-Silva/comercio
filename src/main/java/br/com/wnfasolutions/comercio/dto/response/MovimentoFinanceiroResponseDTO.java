package br.com.wnfasolutions.comercio.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.wnfasolutions.comercio.enuns.FormaPagamento;
import br.com.wnfasolutions.comercio.enuns.Situacao;
import br.com.wnfasolutions.comercio.enuns.Status;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovimentoFinanceiroResponseDTO {

	@ApiModelProperty(notes = "Identificador único do movimento financeiro", required = true, example = "01")
	private Long id;
	
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(notes = "Data da inclusão do movimento financeiro", required = true, example = "21/07/2023")
	private LocalDate dataInclusao;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(notes = "Data útima atualização do movimento financeiro", required = false, example = "22/07/2023")
	private LocalDate dataAtualizacao;
	
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

	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(notes = "Data que foi realizado o pagamento do movimento financeiro.", required = true, example = "20/07/2050")
	private LocalDate dataPagamento;
	
	@NotNull
	@ApiModelProperty(notes = "Documento ou Parcela do movimento financeiro", required = true, example = "Comissão O.S 1")
	private String documentoParcela;

	@NotNull
	@ApiModelProperty(notes = "Forma de pagamento do movimento financeiro", required = true, example = "DINHEIRO")
	@Enumerated(EnumType.STRING)
	private FormaPagamento formaPagamento;
	
	@ApiModelProperty(notes = "Situação atual do movimento financeiro", required = true, example = "ATIVO")
	@Enumerated(EnumType.STRING)
	private Situacao situacao;

	@ApiModelProperty(notes = "Status atual do mvimento financeiro", required = true, example = "PENDENTE")
	@Enumerated(EnumType.STRING)
	private Status status;
}
