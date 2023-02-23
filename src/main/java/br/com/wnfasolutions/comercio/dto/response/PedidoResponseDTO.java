package br.com.wnfasolutions.comercio.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
public class PedidoResponseDTO {
	
	@ApiModelProperty(notes = "Identificador único do pedido.", required = true, example = "01")
	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@ApiModelProperty(notes = "Data de realização do pedido.", required = true, example = "21/07/2049")
	private LocalDateTime dataInclusao;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@ApiModelProperty(notes = "Data de alteração do pedido.", required = true, example = "21/07/2050")
	private LocalDateTime dataAlteracao;
	
	@ApiModelProperty(notes = "Situação atual do pedido.", required = true)
	private String situacaoPedido;
	
	@ApiModelProperty(notes = "Dados do orçamento que será aprovado e convertido em pedido.", required = true)
	private OrcamentoResponseDTO orcamento;
}
