package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Enum.ResponseStatus;
import com.example.demo.dto.Response;
import com.example.demo.entity.Payment;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceIMP implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    @Override
    public Response<?> prisist(Payment payment) {
        if (payment.hasId()) {
            payment.setId(null);
        }

        if (userRepository.existsByEmail(payment.getEmail())) {
            paymentRepository.save(payment);
            return new Response<>(ResponseStatus.SUCCESS, "Payment saved successfully");
        } else {
            return new Response<>(ResponseStatus.ERROR, "Email not found please give me registered Email");
        }
    }

    @Override
    public Response<?> merge(Payment payment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'merge'");
    }

    @Override
    public Response<List<Payment>> findAll() {
        List<Payment> payment = paymentRepository.findAll();
        return new Response<>(ResponseStatus.SUCCESS, null, payment);
    }

    @Override
    public Response<String> deleteById(Integer id) {
        paymentRepository.deleteById(id);
        return new Response<>(ResponseStatus.SUCCESS, "Payment deleted successfully");

    }

    @Override
    public Response<Payment> findById(Integer id) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        return new Response<>(ResponseStatus.SUCCESS, null, payment);
    }

    @Override
    public Response<Page<Payment>> findAllByPage(Pageable pageable, String search) {
        Page<Payment> payment;
        if (search == null || search.equals("*")) {
            payment = paymentRepository.findAll(pageable);
        } else {
            payment = paymentRepository.search(search, pageable);
        }
        return new Response<>(ResponseStatus.SUCCESS, null, payment);
    }

}
