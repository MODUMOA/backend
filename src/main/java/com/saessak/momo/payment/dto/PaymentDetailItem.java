package com.saessak.momo.payment.dto;

import lombok.Data;

@Data
public class PaymentDetailItem {
    int paymentIdx;
    int trashIdx;
    int trashWeight;
    double price;
}
