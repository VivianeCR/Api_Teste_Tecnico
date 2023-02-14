package br.gov.pa.sefa.fadesp.payment.desafio_tecnico.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pa.sefa.fadesp.payment.desafio_tecnico.dto.PaymentDTO;
import br.gov.pa.sefa.fadesp.payment.desafio_tecnico.model.PaymentModel;
import br.gov.pa.sefa.fadesp.payment.desafio_tecnico.model.PaymentType;
import br.gov.pa.sefa.fadesp.payment.desafio_tecnico.model.ProcessingStatus;
import br.gov.pa.sefa.fadesp.payment.desafio_tecnico.services.PaymentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;



@RestController
@AllArgsConstructor
@RequestMapping(value = "/pagamentos")
public class PaymentController {

    private final PaymentService paymentService;
    private final ModelMapper modelMapper;


    @GetMapping(value="/buscarTodos")
    public ResponseEntity<List<PaymentDTO>> getMethodName() {
        List<PaymentModel> paymentList =  this.paymentService.findAll();
        return ResponseEntity.ok().body(convertToDTO(paymentList));
    }

    @GetMapping(value="/buscarTodo", params = "codigo")
    public ResponseEntity<PaymentDTO> buscarPorStatus(@RequestParam Integer codigo) {
        PaymentModel payment = paymentService.findById(codigo);
        return ResponseEntity.ok().body(convertToDTO(payment));
    }   

    @GetMapping(value="/buscarTodos", params = "processingStatus")
    public ResponseEntity<List<PaymentDTO>> buscarPorStatus(@RequestParam String processingStatus) {
        List<PaymentModel> paymentList = paymentService.findByProcessStatus(ProcessingStatus.valueOf(processingStatus));
        return ResponseEntity.ok().body(convertToDTO(paymentList));
    }

    @GetMapping(value="/buscarTodos", params = "paymentType")
    public ResponseEntity<List<PaymentDTO>> buscarPorTipy(@RequestParam String paymentType) {
        List<PaymentModel> paymentList = paymentService.findByPaymentType(PaymentType.valueOf(paymentType));
        return ResponseEntity.ok().body(convertToDTO(paymentList));
    }
    

    @PostMapping(value = "/salvar")
    public ResponseEntity<PaymentDTO> salvar(@Valid @RequestBody PaymentDTO paymentDTO){
        PaymentModel paymentModel = convertToEntity(paymentDTO);
        paymentModel = paymentService.save(paymentModel);
        return ResponseEntity.ok(convertToDTO(paymentModel));
    }

    @PutMapping(value = "/{codigo}/update")
    public ResponseEntity<PaymentDTO> update(@Valid @RequestBody PaymentDTO paymentDTO, @PathVariable Integer codigo){
        if (paymentDTO.getCodigo() == codigo) {
            PaymentModel paymentModel = convertToEntity(paymentDTO);
            paymentModel = paymentService.save(paymentModel);
            return ResponseEntity.ok(convertToDTO(paymentModel));
        }
        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping(value = "/{codigo}/delete")
    public ResponseEntity<Boolean> delete(@PathVariable Integer codigo){
        return ResponseEntity.badRequest().body(paymentService.delete(codigo));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    private PaymentModel convertToEntity(PaymentDTO paymentDTO){
        TypeMap<PaymentDTO, PaymentModel> typeMap = modelMapper.typeMap(PaymentDTO.class, PaymentModel.class);
        return typeMap.map(paymentDTO);
    }

    private PaymentDTO convertToDTO(PaymentModel paymentModel){
        return this.modelMapper.map(paymentModel, PaymentDTO.class);
    }
    
    private List<PaymentDTO> convertToDTO(List<PaymentModel> paymentModel){
        List<PaymentDTO> paymentDTOs = new ArrayList<>(0);
        paymentModel.forEach(model -> paymentDTOs.add(convertToDTO(model)));
        return paymentDTOs;
    }

}
