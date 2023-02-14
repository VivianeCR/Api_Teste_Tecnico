package br.gov.pa.sefa.fadesp.payment.desafio_tecnico.model;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Pagamento")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PaymentModel {
    
    @Id 
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pagamento")
    private PaymentType paymentType;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProcessingStatus processingStatus;

    @Column(name = "cpf", length = 14)
    private String cpf;

    @Column(name = "cnpj", length = 15)
    private String cnpj;

    @Column(name = "numero_cartao", unique = true, length = 16)
    private String numCartao;
    
    @Column(name = "valor")
    private BigDecimal valor;
}
