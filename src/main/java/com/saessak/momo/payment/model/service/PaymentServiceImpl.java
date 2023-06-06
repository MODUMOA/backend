package com.saessak.momo.payment.model.service;

import com.saessak.momo.payment.model.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl {
    private final PaymentMapper paymentMapper;
}
