package br.com.wnfasolutions.comercio.dto.response;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.wnfasolutions.comercio.enuns.Situacao;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServicoResponseDTO {

	@ApiModelProperty(notes = "Identificador único para o serviço", required = true, example = "01")
	private Long id;
	
	@ApiModelProperty(notes = "Nome do serviço", required = true, example = "Barra de calça jeans")
    private String nome;

	@ApiModelProperty(notes = "Valor do serviço", required = true, example = "20.00")
    private double valor;
    
	@ApiModelProperty(notes = "Situação atual do serviço", required = true, example = "ATIVO")
    @Enumerated(EnumType.STRING)
    private Situacao situacao;
}
