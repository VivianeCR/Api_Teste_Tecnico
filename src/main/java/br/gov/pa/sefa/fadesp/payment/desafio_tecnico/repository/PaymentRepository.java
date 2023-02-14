package br.gov.pa.sefa.fadesp.payment.desafio_tecnico.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.gov.pa.sefa.fadesp.payment.desafio_tecnico.model.PaymentModel;
import br.gov.pa.sefa.fadesp.payment.desafio_tecnico.model.PaymentType;
import br.gov.pa.sefa.fadesp.payment.desafio_tecnico.model.ProcessingStatus;

public interface PaymentRepository extends CrudRepository<PaymentModel, Integer> {
    
    List<PaymentModel> findAll();
    List<PaymentModel> findByProcessingStatus(ProcessingStatus processingStatus);
    List<PaymentModel> findByPaymentType(PaymentType paymentType);
}
