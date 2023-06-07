package com.saessak.momo.payment.dto;

import lombok.Data;

@Data
public class PaymentDetailItem {
    int paymentIdx;
    int paymentDetailIdx;
    int trashIdx;
    String category;
    int trashWeight;
    double price;
}
