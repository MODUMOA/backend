package com.saessak.momo.payment.dto;

import lombok.Data;

@Data
public class PaymentStatusItem {
    int paymentIdx;
    int status;         // 결제 수단 (상태)
}
