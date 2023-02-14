package br.gov.pa.sefa.fadesp.payment.desafio_tecnico.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PaymentDTO {
    
    private Integer codigo;

    @NotEmpty(message = "Não pode ser vazxio")
    private String paymentType;

    private String processingStatus;

    private String cpf;

    private String cnpj;

    private String numCartao;
    
    private BigDecimal valor;
}
