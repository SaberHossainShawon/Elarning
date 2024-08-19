package com.example.demo.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Response;
import com.example.demo.entity.Payment;
import com.example.demo.service.PaymentService;
import com.example.demo.util.PageUtil;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController implements BaseController<Payment, Integer> {

    private final PaymentService paymentService;

    @Override
    public ResponseEntity<Response<?>> save(Payment payment) {
        if (payment.hasId()) {
            return ResponseEntity.ok(paymentService.merge(payment));
        } else {
            return ResponseEntity.ok(paymentService.prisist(payment));
        }
    }

    @Override
    public ResponseEntity<Response<?>> deleteById(Integer id) {
        return ResponseEntity.ok(paymentService.deleteById(id));
    }

    @Override
    public ResponseEntity<Response<Payment>> findById(Integer id) {
        Response<Payment> response = paymentService.findById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<List<Payment>>> findAll() {
        Response<List<Payment>> response = paymentService.findAll();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<Page<Payment>>> findAllByPage(Integer pageNumber, Integer pageSize,
            String sortColumn, String order, String search) {
        Pageable pageable = PageUtil.getPageable(pageNumber, pageSize, sortColumn, order);
        Response<Page<Payment>> response = paymentService.findAllByPage(pageable, search);
        return ResponseEntity.ok(response);
    }

   

}
