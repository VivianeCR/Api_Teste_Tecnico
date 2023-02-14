package br.gov.pa.sefa.fadesp.payment.desafio_tecnico.model;

public enum PaymentType {
    BOLETO,
    CARTAO_CREDITO,
    CARTAO_DEBITO,
    PIX;

    private PaymentType(){}

    public static PaymentType getPaymentType(String key) {
        for (PaymentType paymentType : PaymentType.values()) {
            if (paymentType.toString().equals(key)) {
                return paymentType;
            }
        }
        return null;
    }
}
