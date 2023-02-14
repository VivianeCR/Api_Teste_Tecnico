package br.gov.pa.sefa.fadesp.payment.desafio_tecnico.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.gov.pa.sefa.fadesp.payment.desafio_tecnico.model.PaymentModel;
import br.gov.pa.sefa.fadesp.payment.desafio_tecnico.model.PaymentType;
import br.gov.pa.sefa.fadesp.payment.desafio_tecnico.model.ProcessingStatus;
import br.gov.pa.sefa.fadesp.payment.desafio_tecnico.repository.PaymentRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;   

    public List<PaymentModel> findAll(){
        return paymentRepository.findAll();
    }

    public PaymentModel save(PaymentModel paymentModel){
        if (paymentModel.getCodigo() == null) {
            long id = paymentRepository.count();
            paymentModel.setCodigo(id++);
        }
        return paymentRepository.save(paymentModel);
    }

    public PaymentModel findById(Integer codigo){
        return paymentRepository.findById(codigo).orElse(null);
    }

    public Boolean delete(Integer codigo){
        paymentRepository.deleteById(codigo);
        return !paymentRepository.existsById(codigo);
    }

    public List<PaymentModel> findByProcessStatus(ProcessingStatus processingStatus){
        return paymentRepository.findByProcessingStatus(processingStatus);
    }
    
    public List<PaymentModel> findByPaymentType(PaymentType paymentType){
        return paymentRepository.findByPaymentType(paymentType);
    }

    
}
